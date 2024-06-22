import {
  fetchUtils,
  GET_LIST,
  GET_ONE,
  GET_MANY,
  GET_MANY_REFERENCE,
  CREATE,
  UPDATE,
  UPDATE_MANY,
  DELETE,
  DELETE_MANY,
} from 'react-admin';
import { stringify } from 'query-string';

const isString = str => typeof str === 'string' || str instanceof String;

export default (apiUrl, httpClient = fetchUtils.fetchJson) => {

  const convertDataRequestToHTTP = (type, resource, params) => {
    let url = '';
    const options = {};
    switch (type) {
      case GET_LIST: {
        const { filter } = params;
        const { page, perPage } = params.pagination;
        const { field, order } = params.sort;
        const search = { filter: filter.q, ...filter };
        url = `${apiUrl}/${resource}?page=${page -
          1}&size=${perPage}&sort=${field}:${order.toLowerCase()}&${stringify(
          search
        )}`;
        // &filter=${filter.q ? filter.q : ''}`;
        break;
      }
      case GET_ONE:
        url = `${apiUrl}/${resource}/${params.id}`;
        break;
      case GET_MANY: {
        url = `${apiUrl}/${resource}/many?ids=${params.ids}`;
        break;
      }
      case GET_MANY_REFERENCE: {
        const { page } = params.pagination;
        url = `${apiUrl}/${resource}?page=${page}`;
        break;
      }
      case UPDATE:
        url = `${apiUrl}/${resource}/${params.id}`;
        options.method = 'PUT';
        options.body = JSON.stringify(params.data);
        break;
      case CREATE:
        url = `${apiUrl}/${resource}`;
        options.method = 'POST';
        options.body = JSON.stringify(params.data);
        break;
      case DELETE:
        url = `${apiUrl}/${resource}/${params.id}`;
        options.method = 'DELETE';
        break;
      case 'GET':
        url = `${apiUrl}/${resource}`;
        options.method = type;
        break;
      case 'POST':
      case 'PUT':
        url = `${apiUrl}/${resource}`;
        options.method = type;
        options.body = isString(params) ? params : JSON.stringify(params);
        break;
      default:
        throw new Error(`Unsupported fetch action type ${type}`);
    }
    return { url, options };
  };

  const convertHTTPResponse = (response, type, resource, params) => {
    if (!response || !response.json) return { data: {} };
    const { json } = response;
    switch (type) {
      case GET_LIST:
      case GET_MANY_REFERENCE:
        return {
          data: json ? json.content : [],
          total: parseInt(json ? json.totalElements : 0, 10),
        };
      case CREATE:
        return { data: { ...params.data, id: json.id } };
      default:
        return { data: json };
    }
  };

  return (type, resource, params) => {
    // simple-rest doesn't handle filters on UPDATE route, so we fallback to calling UPDATE n times instead
    if (type === UPDATE_MANY) {
      return Promise.all(
        params.ids.map(id =>
          httpClient(`${apiUrl}/${resource}/${id}`, {
            method: 'PUT',
            body: JSON.stringify(params.data),
          })
        )
      ).then(responses => ({
        data: responses.map(response => response.json),
      }));
    }
    // simple-rest doesn't handle filters on DELETE route, so we fallback to calling DELETE n times instead
    if (type === DELETE_MANY) {
      return Promise.all(
        params.ids.map(id =>
          httpClient(`${apiUrl}/${resource}/${id}`, {
            method: 'DELETE',
          })
        )
      ).then(responses => ({
        data: responses.map(response => response.json),
      }));
    }

    const { url, options } = convertDataRequestToHTTP(type, resource, params);
    return httpClient(url, options)
      .then(response => convertHTTPResponse(response, type, resource, params))
      .catch(error => {
        const { status, body } = error;
        if (!status) {
          throw new Error('Serviço indisponível, tente novamente mais tarde!');
          // } else if (status === 401) {
          //   localStorage.removeItem('token');
          //   throw new Error('Sessão expirada!');
        } else if (status === 403) {
          throw new Error(body.error_description);
        } else if (body.exceptions && !body.message) {
          let m = '';
          Object.keys(body.exceptions).forEach(key => {
            m += `${key}: ${body.exceptions[key]}; `;
          });
          error.message = m;
          throw error;
        } else throw error;
      });
  };
};
