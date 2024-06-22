import { Box, Grid, Toolbar } from '@material-ui/core';
import LocalAtmIcon from '@material-ui/icons/LocalAtm';
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

export const RevenueList = props => (
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
            <LocalDateField source="purchaseDate" />
            <LocalDateField source="dueDate" />
            <TextField source="description" />
            <TextField source="category" />
            <EnumRadioField source="status" type="status"/>
        </Datagrid>
    </List>
);

const RevenueForm = props => {
    return (
        <FormWithRedirect
            {...props}
            redirect={false}
            render={formProps => (
                <form onSubmit={formProps.submit}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <TextInput resource="revenues" source="id" disabled />
                        </Grid>
                        <Grid item xs={3}>
                            <TextInput resource="revenues" source="createdAt" disabled />
                        </Grid>
                        <Grid item xs={3}>
                            <DateTimeInput resource="revenues" source="updatedAt" disabled />
                        </Grid>
                        <Grid item xs={3}>
                            <TextInput resource="revenues" source="description" validate={required()}
                            />
                        </Grid>
                        <Grid item xs={3}>
                            <NumberInput resource="revenues" source="value" validate={required()}
                            />
                        </Grid>
                        <Grid item xs={3}>
                            <TextInput resource="revenues" source="category" validate={required()} />
                        </Grid>
                        <Grid item xs={4}>
                            <EnumRadioInput resource="revenues" source="status" type="status" />
                        </Grid>
                        <Grid item xs={4}>
                            <DateInput resource="revenues" source="purchaseDate"/>
                        </Grid>
                        <Grid item xs={4}>
                            <DateInput resource="revenues" source="dueDate"/>
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

const RevenueTabs = props => {
    const [value, setValue] = React.useState(0);
    return (
        <div>
            <TabPanel value={value} index={0}>
                <RevenueForm {...props} />
            </TabPanel>
        </div>
    );
};

export const RevenueEdit = props => {
    return (
        <Edit mutationMode={false} actions={<FormActions />} {...props}>
            <RevenueTabs {...props} />
        </Edit>
    );
};

export const RevenueCreate = props => (
    <Create undoable="false" actions={<FormActions />} {...props}>
        <SimpleForm>
            <TextInput source="description" validate={required()} />
            <NumberInput source="value" validate={required()} />
            <TextInput source="category" validate={required()} />
            <DateInput source="purchaseDate" validate={required()} />
            <DateInput source="dueDate" validate={required()} />
            <EnumRadioInput resource="revenues" source="status" type="status" />
                        
        </SimpleForm>
    </Create>
);

const FormActions = ({ basePath }) => (
    <TopToolbar>
        <ListButton basePath={basePath} />
    </TopToolbar>
);

export default {
    create: RevenueCreate,
    edit: RevenueEdit,
    list: RevenueList,
    icon: LocalAtmIcon,
};
