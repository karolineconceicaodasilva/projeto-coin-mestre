import { Box, Grid, Toolbar } from '@material-ui/core';
import MonetizationOnIcon from '@material-ui/icons/MonetizationOn';
import React from 'react';
import {
    Create,
    Datagrid,
    DateInput,
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
import { EnumRadioField, EnumRadioInput } from '../components/Enums';
import LocalDateTimeField from '../components/LocalDateTimeField';
import LocalDateField from '../components/LocalDateField';
import { TabPanel } from '../components/TabPanels';
import RestoreButton from '../components/RestoreButton';

export const ExpenseList = props => (
    <List
        filters={<ListFilterWithDeleteds />}
        bulkActionButtons={false}
        sort={{ field: 'status', order: 'ASC' }}
        {...props}
    >
        <Datagrid rowClick="edit">
            <TextField source="id" />
            <LocalDateTimeField source="createdAt" />
            <LocalDateTimeField source="updatedAt" />
            <NumberField source="value" />
            <LocalDateField source="purchaseDate" />
            <LocalDateField source="dueDate" />
            <TextField source="description" />
            <TextField source="category" />
            <EnumRadioField source="status" type="status"/>
        </Datagrid>
    </List>
);

const ExpenseForm = props => {
    return (
        <FormWithRedirect
            {...props}
            redirect={false}
            render={formProps => (
                <form onSubmit={formProps.submit}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <TextInput resource="expenses" source="id" disabled />
                        </Grid>
                        <Grid item xs={3}>
                            <TextInput resource="expenses" source="createdAt" disabled />
                        </Grid>
                        <Grid item xs={3}>
                            <DateTimeInput resource="expenses" source="updatedAt" disabled />
                        </Grid>
                        <Grid item xs={3}>
                            <TextInput resource="expenses" source="description" validate={required()}
                            />
                        </Grid>
                        <Grid item xs={3}>
                            <NumberInput resource="expenses" source="value" validate={required()}
                            />
                        </Grid>
                        <Grid item xs={3}>
                            <TextInput resource="expenses" source="category" validate={required()} />
                        </Grid>
                        <Grid item xs={4}>
                            <EnumRadioInput resource="expenses" source="status" type="status" />
                        </Grid>
                        <Grid item xs={4}>
                            <DateInput resource="expenses" source="purchaseDate"/>
                        </Grid>
                        <Grid item xs={4}>
                            <DateInput resource="expenses" source="dueDate"/>
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

const ExpenseTabs = props => {
    const [value, setValue] = React.useState(0);
    return (
        <div>
            <TabPanel value={value} index={0}>
                <ExpenseForm {...props} />
            </TabPanel>
        </div>
    );
};

export const ExpenseEdit = props => {
    return (
        <Edit mutationMode={false} actions={<FormActions />} {...props}>
            <ExpenseTabs {...props} />
        </Edit>
    );
};

export const ExpenseCreate = props => (
    <Create undoable="false" actions={<FormActions />} {...props}>
        <SimpleForm>
            <TextInput source="description" validate={required()} />
            <NumberInput source="value" validate={required()} />
            <TextInput source="category" validate={required()} />
            <DateInput source="purchaseDate" validate={required()} />
            <DateInput source="dueDate" validate={required()} />
            <EnumRadioInput resource="expenses" source="status" type="status" />
                        
        </SimpleForm>
    </Create>
);

const FormActions = ({ basePath }) => (
    <TopToolbar>
        <ListButton basePath={basePath} />
    </TopToolbar>
);

export default {
    create: ExpenseCreate,
    edit: ExpenseEdit,
    list: ExpenseList,
    icon: MonetizationOnIcon,
};
