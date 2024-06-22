import { createMuiTheme } from '@material-ui/core/styles';

const theme = createMuiTheme({
  props: {
    MuiButtonBase: {
      disableRipple: true,
    },
  },
  palette: {
    primary: { main: '#000', expense: '#4682B4' },
    secondary: { main: '#213A5C' },
    contrastThreshold: 3,
    tonalOffset: 0.2,
  },
  overrides: {
    MuiAppBar: {
      root: {
        backgroundColor: '#213A5C !important',
      },
      colorSecondary: {
        color: '#000',
      },
    },
    RaTopToolbar: {
      root: {
        paddingTop: 8,
      },
    },
    MuiFormControl: {
      root: {
        width: '100%',
      },
      marginNormal: {
        marginTop: 0,
        marginBottom: 0,
      },
      marginDense: {
        marginTop: 0,
        marginBottom: 0,
      },
    },
    MuiFilledInput: {
      root: {
        backgroundColor: 'unset',
      },
    },
  },
});

export default theme;
