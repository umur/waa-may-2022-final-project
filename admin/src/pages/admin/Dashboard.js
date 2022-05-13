import { faker } from "@faker-js/faker";
import * as React from "react";
import dayjs from "dayjs";
import { Box, Grid, Paper } from "@mui/material";
import DataTable from "../../components/DataTable";
import Layout from "./Layout";
import ReactECharts from 'echarts-for-react';

// Import the echarts core module, which provides the necessary interfaces for using echarts.
import * as echarts from "echarts/core";
// Import charts, all with Chart suffix
import {
  // LineChart,
  BarChart,
  PieChart,
  // ScatterChart,
  // RadarChart,
  // MapChart,
  // TreeChart,
  // TreemapChart,
  // GraphChart,
  // GaugeChart,
  // FunnelChart,
  // ParallelChart,
  // SankeyChart,
  // BoxplotChart,
  // CandlestickChart,
  // EffectScatterChart,
  // LinesChart,
  // HeatmapChart,
  // PictorialBarChart,
  // ThemeRiverChart,
  // SunburstChart,
  // CustomChart,
} from "echarts/charts";
// import components, all suffixed with Component
import {
  // GridSimpleComponent,
  GridComponent,
  // PolarComponent,
  // RadarComponent,
  // GeoComponent,
  // SingleAxisComponent,
  // ParallelComponent,
  CalendarComponent,
  // GraphicComponent,
  ToolboxComponent,
  TooltipComponent,
  // AxisPointerComponent,
  // BrushComponent,
  TitleComponent,
  TimelineComponent,
  // MarkPointComponent,
  // MarkLineComponent,
  // MarkAreaComponent,
  LegendComponent,
  // LegendScrollComponent,
  // LegendPlainComponent,
  DataZoomComponent,
  // DataZoomInsideComponent,
  // DataZoomSliderComponent,
  VisualMapComponent,
  // VisualMapContinuousComponent,
  // VisualMapPiecewiseComponent,
  // AriaComponent,
  // TransformComponent,
  // DatasetComponent,
} from "echarts/components";
// Import renderer, note that introducing the CanvasRenderer or SVGRenderer is a required step
import {
  CanvasRenderer,
  // SVGRenderer,
} from "echarts/renderers";

// Register the required components
echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  CanvasRenderer,
  PieChart,
  VisualMapComponent,
  LegendComponent,
  ToolboxComponent,
  DataZoomComponent,
  TimelineComponent,
  CalendarComponent,
  BarChart,
]);

const option = {
  title: {
    text: "Total income",
    subtext: "",
    x: "center",
  },
  tooltip: {
    trigger: "item",
    formatter: "{a} <br/>{b} : {c} ({d}%)",
  },
  legend: {
    orient: "vertical",
    left: "left",
    data: ["Property A", "Property B", "Property C", "Property D", "Property E"],
  },
  series: [
    {
      name: "Income",
      type: "pie",
      radius: "55%",
      center: ["50%", "60%"],
      data: [
        { value: 335, name: "Property A", id: 1 },
        { value: 310, name: "Property B", id: 2 },
        { value: 234, name: "Property C", id: 3 },
        { value: 135, name: "Property D", id: 4 },
        { value: 1548, name: "Property E", id: 5 },
      ],
      itemStyle: {
        emphasis: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: "rgba(0, 0, 0, 0.5)",
        },
      },
    },
  ],
};

const lineChartOption = {
  title: {
    text: 'Number of properties rented'
  },
  toolbox: {
      feature: {
          saveAsImage: {},
          dataZoom: {},
          restore: {}
      }
  },
  tooltip: {},
  legend: {
    data:['Properties']
  },
  xAxis: {
    data: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7']
  },
  yAxis: {},
  series: [{
    name: 'Properties',
    type: 'line',
    data: [5, 20, 36, 10, 10, 20, 0]
  }]
};

function generateRow(index) {
  return {
    id: `${index}`,
    name: faker.name.findName(),
    date: `${faker.date.past()}`,
    streetName: faker.address.streetName(),
    image: faker.image.image(),
  };
}

function generateData(page, perPage, rowCount, orderBy, orderDirection) {
  let noOfItems = perPage;
  let nextPage = page + 1;

  if (nextPage * perPage > rowCount) {
    noOfItems = rowCount - page * perPage;
  }

  const data = [];

  for (let index = 1; index <= noOfItems; index++) {
    const id = index + page * perPage;

    data.push(generateRow(id));
  }

  return data;
}

function Dashboard(props) {
  const columns = [
    { id: "id", label: "Id", minWidth: 170 },
    { id: "name", label: "Name", minWidth: 170 },
    {
      id: "date",
      label: "Date",
      minWidth: 170,
      align: "right",
      format: (value) => dayjs(value).format("MMM DD YYYY"),
    },
    {
      id: "streetName",
      label: "Address",
      minWidth: 170,
      align: "right",
      format: (value) => value,
    },
    // {
    //   id: "image",
    //   label: "Photo",
    //   minWidth: 170,
    //   align: "right",
    //   renderCell: (params) => (
    //     <img width={170} src={params.value} alt="property" />
    //   ),
    // },
  ];

  const [order, setOrder] = React.useState("desc");
  const [orderBy, setOrderBy] = React.useState("name");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const [rows, setRows] = React.useState([]);
  const [rowCount, setRowCount] = React.useState(34);

  const fetchData = React.useCallback(() => {
    setRows(generateData(page, rowsPerPage, rowCount, orderBy, order));
  }, [order, orderBy, page, rowsPerPage, rowCount]);

  React.useEffect(() => {
    fetchData();
  }, [fetchData]);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  // const handleSelectAllClick = (event) => {
  //   if (event.target.checked) {
  //     const newSelecteds = rows.map((n) => n.name);
  //     setSelected(newSelecteds);
  //     return;
  //   }
  //   setSelected([]);
  // };

  const handleClick = (event, name) => {
    const selectedIndex = selected.indexOf(name);
    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, name);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1)
      );
    }

    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  /* -------------------------------------------------------------------------- */
  /*                                    Chart                                   */
  /* -------------------------------------------------------------------------- */
  const getOption = () => {
    return option;
  };

  function onChartReady(echarts) {
    console.log('echarts is ready', echarts);
  }

  function onChartClick(param, echarts) {
    console.log(param, echarts);
  };

  function onChartLegendselectchanged(param, echarts) {
    console.log(param, echarts);
  };

  /* -------------------------------------------------------------------------- */
  /*                                  End Chart                                 */
  /* -------------------------------------------------------------------------- */

  return (
    <Layout title="Dashboard">
      <Grid container spacing={1}>
        {/* Recent Properties */}
        <Grid item xs={6}>
          <DataTable
            title={"Recent Properties"}
            order={order}
            orderBy={orderBy}
            selected={selected}
            rows={rows}
            columns={columns}
            handleRequestSort={handleRequestSort}
            handleClick={handleClick}
            rowCount={rowCount}
            rowsPerPage={rowsPerPage}
            page={page}
            handleChangePage={handleChangePage}
            handleChangeRowsPerPage={handleChangeRowsPerPage}
          />
        </Grid>
        <Grid item xs={6} rowSpacing={1}>
          <Paper>
              <ReactECharts
                option={getOption()}
                style={{ height: 400 }}
                onChartReady={onChartReady}
                onEvents={{
                  click: onChartClick,
                  legendselectchanged: onChartLegendselectchanged,
                }}
              />
          </Paper>
          <Box margin={1} />
          <Paper>
            <ReactECharts
              option={lineChartOption}
              style={{ height: 400 }}
            />
          </Paper>
        </Grid>
      </Grid>
    </Layout>
  );
}

export default Dashboard;
