import ptbrMessages from 'ra-language-portuguese';

ptbrMessages.messages = {
  required: 'Preenchimento obrigatório',
  loading: 'Carregando',
  wait: 'Aguarde',
};

ptbrMessages.page = {
  empty: 'Nenhum registro encontrado',
  invite: 'Deseja criar um novo?',
};

ptbrMessages.enums = {
  roles: {
    ADMIN: 'Admin',
    USER: 'User',
  },
  status: {
    OPEN: 'Aberto',
    CLOSE: 'Pago',
    OVERDUE: 'Vencido'
  }
};

ptbrMessages.forms = {
  summary: 'Cadastro',
};

ptbrMessages.mbrnTable = {
  pagination: {
    labelDisplayedRows: '{from} - {to} de {count}',
    labelRowsSelect: 'registros',
    labelRowsPerPage: 'Registros por página:',
    firstAriaLabel: 'Primeira página',
    firstTooltip: 'Primeira página',
    previousAriaLabel: 'Página anterior',
    previousTooltip: 'Página anterior',
    nextAriaLabel: 'Próxima página',
    nextTooltip: 'Próxima página',
    lastAriaLabel: 'Última página',
    lastTooltip: 'Última página',
  },
  toolbar: {
    nRowsSelected: '{0} linha(s) selecionada',
  },
  header: {
    actions: 'Ações',
  },
  body: {
    emptyDataSourceMessage: 'Nenhum registro encontrado',
    filterRow: {
      filterTooltip: 'Filtrar',
    },
  },
};

ptbrMessages.resources = {
  users: {
    empty: ptbrMessages.page.empty,
    invite: ptbrMessages.page.invite,
    name: 'Usuário |||| Usuários',
    fields: {
      createdAt: 'Criado em',
      updatedAt: 'Atualizado em',
      username: 'Login',
      name: 'Nome Completo',
      password: 'Senha',
      confirmationPassword: 'Confirmação de Senha',
      email: 'E-mail',
      roles: 'Permissões'
    },
  },
  
  expenses: {
    empty: ptbrMessages.page.empty,
    invite: ptbrMessages.page.invite,
    name: 'Despesa |||| Despesas',
    fields: {
      createdAt: 'Criado em',
      updatedAt: 'Atualizado em',
      description: 'Descrição',
      value: 'Valor',
      category: 'Categoria',
      purchaseDate: 'Data de emissão',
      dueDate: 'Vencimento',
      status: 'Status'
    },
  },

  revenues: {
    empty: ptbrMessages.page.empty,
    invite: ptbrMessages.page.invite,
    name: 'Receita |||| Receitas',
    fields: {
      createdAt: 'Criado em',
      updatedAt: 'Atualizado em',
      description: 'Descrição',
      value: 'Valor',
      category: 'Categoria',
      purchaseDate: 'Data de emissão',
      dueDate: 'Vencimento',
      status: 'Status'
    },
  },

  budget: {
    empty: ptbrMessages.page.empty,
    invite: ptbrMessages.page.invite,
    name: 'Orçamentos |||| Orçamentos',
    fields: {
      createdAt: 'Criado em',
      updatedAt: 'Atualizado em',
      goal: 'Objetivo',
      totalReached: 'Total Alcançado',
      totalGoal: 'Total do Objetivo'
      
    },
  },
};

export default ptbrMessages;
