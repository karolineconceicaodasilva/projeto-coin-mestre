import { Box, Grid, Toolbar } from '@material-ui/core';
import PeopleIcon from '@material-ui/icons/People';
import React from 'react';
import {
  Create,
  Datagrid,
  DateTimeInput,
  DeleteButton,
  Edit,
  FormWithRedirect,
  List,
  ListButton,
  PasswordInput,
  required,
  SaveButton,
  SimpleForm,
  TextField,
  TextInput,
  TopToolbar,
} from 'react-admin';
import { ListFilterWithDeleteds } from '../components/ListFilter';
import LocalDateTimeField from '../components/LocalDateTimeField';
import { TabPanel } from '../components/TabPanels';
import RestoreButton from '../components/RestoreButton';
import { EnumCheckboxInput, EnumField } from '../components/Enums';

export const UserList = props => (
  <List
    filters={<ListFilterWithDeleteds />}
    bulkActionButtons={false}
    sort={{ field: 'name', order: 'ASC' }}
    {...props}
  >
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <LocalDateTimeField source="createdAt" />
      <LocalDateTimeField source="updatedAt" />
      <TextField source="name" />
      <TextField source="email" />
    </Datagrid>
  </List>
);

const UserForm = props => {
  return (
    <FormWithRedirect
      {...props}
      redirect={false}
      render={formProps => (
        <form onSubmit={formProps.submit}>
          <Grid container spacing={2}>
            <Grid item xs={3}>
              <TextInput resource="users" source="id" disabled />
            </Grid>
            <Grid item xs={3}>
              <TextInput resource="users" source="updatedBy" disabled />
            </Grid>
            <Grid item xs={3}>
              <DateTimeInput resource="users" source="updatedAt" disabled />
            </Grid>
            <Grid item xs={3}>
              <TextInput resource="users" source="password" type="password" />
            </Grid>
            <Grid item xs={3}>
              <TextInput resource="users" source="confirmationPassword" type="password" />
            </Grid>
            <Grid item xs={3}>
              <TextInput resource="users" source="email" validate={required()} />
            </Grid>
            <Grid item xs={3}>
              <TextInput resource="users" source="name" validate={required()} />
            </Grid>
            <Grid item xs={5}>
              <EnumCheckboxInput resource="users" source="roles" />
            </Grid>
          </Grid>
          <Toolbar disableGutters>
            <Box display="flex" justifyContent="space-between" width="100%">
              <SaveButton
                saving={formProps.saving}
                handleSubmitWithRedirect={formProps.handleSubmitWithRedirect}
              />
              {!formProps.record.deleted ? (
                <DeleteButton
                  record={formProps.record}
                  resource={formProps.resource}
                  basePath={formProps.basePath}
                  undoable={formProps.undoable}
                />
              ) : (
                <RestoreButton
                  record={formProps.record}
                  resource={formProps.resource}
                  basePath={formProps.basePath}
                  undoable={formProps.undoable}
                />
              )}
            </Box>
          </Toolbar>
        </form>
      )}
    />
  );
};

const UserTabs = props => {
  const [value, setValue] = React.useState(0);
  return (
    <div>
      <TabPanel value={value} index={0}>
        <UserForm {...props} />
      </TabPanel>
    </div>
  );
};

export const UserEdit = props => {
  return (
    <Edit mutationMode={false} actions={<FormActions />} {...props}>
      <UserTabs {...props} />
    </Edit>
  );
};

export const UserCreate = props => (
  <Create undoable="false" actions={<FormActions />} {...props}>
    <SimpleForm>
      <TextInput source="email" validate={required()} />
      <TextInput source="name" validate={required()} />
      <PasswordInput source="password" validate={required()} />
      <PasswordInput source="confirmationPassword" validate={required()} />
      <EnumCheckboxInput source="roles" />
    </SimpleForm>
  </Create>
);

const FormActions = ({ basePath }) => (
  <TopToolbar>
    <ListButton basePath={basePath} />
  </TopToolbar>
);

export default {
  create: UserCreate,
  edit: UserEdit,
  list: UserList,
  icon: PeopleIcon,
};
