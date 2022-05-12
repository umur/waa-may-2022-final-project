import { createTheme } from '@mui/material/styles';

export const theme = createTheme({
  status: {
    danger: '#e53e3e',
  },
  palette: {
    neutral: {
      main: '#64748B',
      contrastText: '#ffffff',
    },
    activate: {
      main: '#229a16',
      contrastText: '#229a16',
    },
    deactivate: {
      main: 'rgb(183, 33, 54)',
      contrastText: 'rgb(183, 33, 54)',
    },
  },
});
