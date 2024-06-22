import React from 'react';
import Card from '@material-ui/core/Card';
import { Box, CardContent, Typography, Grid } from '@material-ui/core';
import {
  Title,
  Labeled
} from 'react-admin';
import DashboardIcon from '@material-ui/icons/Dashboard';
import axios from 'axios';

var expensesTotal = [];
var expenseTotalOpen = [];
var expenseTotalClose = [];
var expenseTotalOverdue = [];

function getExpenseTotal() {

  axios.get('http://localhost:8080/api/expenses/value-of-expenses', {
    headers: {
      'Accept': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  }).then(res => {
    expensesTotal = { valueTotal: res.data.valueTotal, quantit: res.data.quantit }
  })

  axios.get('http://localhost:8080/api/expenses/expenses-open', {
    headers: {
      'Accept': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  }).then(res => {
    expenseTotalOpen = { valueTotal: res.data.valueTotal, quantit: res.data.quantit }
  })

  axios.get('http://localhost:8080/api/expenses/expenses-close', {
    headers: {
      'Accept': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  }).then(res => {
    expenseTotalClose = { valueTotal: res.data.valueTotal, quantit: res.data.quantit }
  })

  axios.get('http://localhost:8080/api/expenses/expenses-overdue', {
    headers: {
      'Accept': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  }).then(res => {
    expenseTotalOverdue = { valueTotal: res.data.valueTotal, quantit: res.data.quantit }
  })
}

var revenuesTotal = [];
var revenueTotalOpen = [];
var revenueTotalClose = [];
var revenueTotalOverdue = [];

function getRevenueTotal() {

  axios.get('http://localhost:8080/api/revenues/value-of-revenues', {
    headers: {
      'Accept': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  }).then(res => {
    revenuesTotal = { valueTotal: res.data.valueTotal, quantit: res.data.quantit }
  })

  axios.get('http://localhost:8080/api/revenues/revenues-open', {
    headers: {
      'Accept': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  }).then(res => {
    revenueTotalOpen = { valueTotal: res.data.valueTotal, quantit: res.data.quantit }
  })

  axios.get('http://localhost:8080/api/revenues/revenues-close', {
    headers: {
      'Accept': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  }).then(res => {
    revenueTotalClose = { valueTotal: res.data.valueTotal, quantit: res.data.quantit }
  })

  axios.get('http://localhost:8080/api/revenues/revenues-overdue', {
    headers: {
      'Accept': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  }).then(res => {
    revenueTotalOverdue = { valueTotal: res.data.valueTotal, quantit: res.data.quantit }
  })
}

export const Dashboard = () => (
  getExpenseTotal(),
  getRevenueTotal(),
  <Card>
    <Title title="Bem Vindo" />
    <CardContent style={{ textAlign: 'center' }}>
      <h1>Coin Mestre Backoffice</h1>

    </CardContent>
    <CardContent style={{ textAlign: 'left' }}>
      <h2> Despesas</h2>
      <Box sx={{ flexGrow: 1, bgcolor: 'primary.expense', padding: '10px', borderRadius: '10px', marginTop: '10px' }}>
        <Grid container spacing={4}>
          <Grid item xs={3} >
            <Labeled label="Total de despesas" style={{ textAlign: 'left' }}></Labeled>
            <Labeled label="Valor total">
              <Typography>{expensesTotal.valueTotal}</Typography>
            </Labeled>
            <Labeled label="Quantidade">
              <Typography>{expensesTotal.quantit}</Typography>
            </Labeled>
          </Grid>


          <Grid item xs={3}>
            <Labeled label="Total de despesas em Aberto"></Labeled>
            <Labeled label="Valor total">
              <Typography>{expenseTotalOpen.valueTotal}</Typography>
            </Labeled>
            <Labeled label="Quantidade">
              <Typography>{expenseTotalOpen.quantit}</Typography>
            </Labeled>
          </Grid>

          <Grid item xs={3}>
            <Labeled label="Total de despesas "></Labeled>
            <Labeled label="Valor total">
              <Typography>{expenseTotalClose.valueTotal}</Typography>
            </Labeled>
            <Labeled label="Quantidade">
              <Typography>{expenseTotalClose.quantit}</Typography>
            </Labeled>
          </Grid>

          <Grid item xs={3}>
            <Labeled label="Total de despesas vencidas"></Labeled>
            <Labeled label="Valor total">
              <Typography>{expenseTotalOverdue.valueTotal}</Typography>
            </Labeled>
            <Labeled label="Quantidade">
              <Typography>{expenseTotalOverdue.quantit}</Typography>
            </Labeled>
          </Grid>
        </Grid>
      </Box>
    </CardContent>

    <CardContent style={{ textAlign: 'Left' }}>
      <h2> Receitas</h2>
      <Box sx={{ flexGrow: 1, bgcolor: 'primary.expense', padding: '10px', borderRadius: '10px', marginTop: '10px' }}>
        <Grid container spacing={4}>
          <Grid item xs={3} >
            <Labeled label="Total de despesas" style={{ textAlign: 'left' }}></Labeled>
            <Labeled label="Valor total">
              <Typography>{revenuesTotal.valueTotal}</Typography>
            </Labeled>
            <Labeled label="Quantidade">
              <Typography>{revenuesTotal.quantit}</Typography>
            </Labeled>
          </Grid>


          <Grid item xs={3}>
            <Labeled label="Total de despesas em Aberto"></Labeled>
            <Labeled label="Valor total">
              <Typography>{revenueTotalOpen.valueTotal}</Typography>
            </Labeled>
            <Labeled label="Quantidade">
              <Typography>{revenueTotalOpen.quantit}</Typography>
            </Labeled>
          </Grid>

          <Grid item xs={3}>
            <Labeled label="Total de despesas " style= {{color: '#7CFC00'}}></Labeled>
            <Labeled label="Valor total">
              <Typography>{revenueTotalClose.valueTotal}</Typography>
            </Labeled>
            <Labeled label="Quantidade">
              <Typography>{revenueTotalClose.quantit}</Typography>
            </Labeled>
          </Grid>

          <Grid item xs={3}>
            <Labeled label="Total de despesas vencidas"></Labeled>
            <Labeled label="Valor total">
              <Typography>{revenueTotalOverdue.valueTotal}</Typography>
            </Labeled>
            <Labeled label="Quantidade">
              <Typography>{revenueTotalOverdue.quantit}</Typography>
            </Labeled>
          </Grid>
        </Grid>
      </Box>
    </CardContent>
  </Card>
);

export default {
  list: Dashboard,
  icon: DashboardIcon,
  options: { label: 'Início' },
};
