import { Box, Grid, Toolbar } from '@material-ui/core';
import MonetizationOnIcon from '@material-ui/icons/MonetizationOn';
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
    NumberField,
    NumberInput,
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

export const BudgetList = props => (
    <List
        filters={<ListFilterWithDeleteds />}
        bulkActionButtons={false}
        sort={{ field: 'id', order: 'ASC' }}
        {...props}
    >
        <Datagrid rowClick="edit">
            <TextField source="id" />
            <LocalDateTimeField source="createdAt" />
            <LocalDateTimeField source="updatedAt" />
            <NumberField source="totalReached" />
            <NumberField source="totalGoal" />
            <TextField source="goal" />
        </Datagrid>
    </List>
);

const BudgetForm = props => {
    return (
        <FormWithRedirect
            {...props}
            redirect={false}
            render={formProps => (
                <form onSubmit={formProps.submit}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <TextInput resource="budget" source="id" disabled />
                        </Grid>
                        <Grid item xs={3}>
                            <TextInput resource="budget" source="createdAt" disabled />
                        </Grid>
                        <Grid item xs={3}>
                            <DateTimeInput resource="budget" source="updatedAt" disabled />
                        </Grid>
                       
                        <Grid item xs={3}>
                            <TextInput resource="budget" source="goal" validate={required()} />
                        </Grid>
                        
                        <Grid item xs={3}>
                            <NumberInput resource="budget" source="totalReached" validate={required()} />
                        </Grid>
                        
                        <Grid item xs={3}>
                            <NumberInput resource="budget" source="totalGoal" disabled />
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

const BudgetTabs = props => {
    const [value, setValue] = React.useState(0);
    return (
        <div>
            <TabPanel value={value} index={0}>
                <BudgetForm {...props} />
            </TabPanel>
        </div>
    );
};

export const BudgetEdit = props => {
    return (
        <Edit mutationMode={false} actions={<FormActions />} {...props}>
            <BudgetTabs {...props} />
        </Edit>
    );
};

export const BudgetCreate = props => (
    <Create undoable="false" actions={<FormActions />} {...props}>
        <SimpleForm>
            <TextInput source="goal" validate={required()} />
            <NumberInput source="totalReached" validate={required()} />
            <NumberInput source="totalGoal" validate={required()} />
                        
        </SimpleForm>
    </Create>
);

const FormActions = ({ basePath }) => (
    <TopToolbar>
        <ListButton basePath={basePath} />
    </TopToolbar>
);

export default {
    create:  BudgetCreate,
    edit:  BudgetEdit,
    list:  BudgetList,
    icon: MonetizationOnIcon,
};
