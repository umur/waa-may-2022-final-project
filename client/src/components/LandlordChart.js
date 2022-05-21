import React, { useEffect, useState } from 'react';
import ReactECharts from 'echarts-for-react';
import { useNavigate } from 'react-router';
import axios from 'axios';
import { getBearer } from '../util/Utility';



function ChartsUser() {
    const bearer = getBearer();
    const [userIncomeState, setUserIncomeState] = useState([])
    const [daysUserState, setDaysUserState] = useState([])
    const [daysUserValueState, setDaysUserValueState] = useState([])

    const uid = localStorage.getItem('uid');

    let userStateData=[];
    const fetchByUser = async () => {
        const result = await axios.get('http://localhost:8080/api/v1/properties/incomes/user/' + uid, {headers: {Authorization: bearer}})
        for(let i=0;i<result.data.length; i++){
            userStateData.push({ value: result.data[i].totalAmount, name: result.data[i].name })
        }
        setUserIncomeState(userStateData)

    }
    let daysUser=[]
    let dayUserValues=[]
    const fetchByWeekUser = async () => {
        const result = await axios.get('http://localhost:8080/api/v1/properties/num-of-properties/' + uid, {headers: {Authorization: bearer}})
        for(let i=0;i<result.data.length; i++){
           daysUser.push(result.data[i].day)
           dayUserValues.push(result.data[i].rented)
        }
        setDaysUserState(daysUser)
        setDaysUserValueState(dayUserValues)       
    }

    useEffect(() => {
        daysUser=[];
        dayUserValues=[];
        userStateData=[];
        fetchByUser();
        fetchByWeekUser();
    }, [])

    const optionPieUser = {
        title: {
            text: 'Total income per location',
            subtext: 'By User',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        series: [
            {
                name: 'Income',
                type: 'pie',
                radius: '50%',
                data:userIncomeState,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    const optionLineUser = {
        title: {
            text: 'Number of properties rented in a week',
            subtext: 'By User',
            left: 'center'
        },
        xAxis: {
            type: 'category',
            data: daysUserState,
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: daysUserValueState,
                type: 'bar'
            }
        ]
    };
    return (
        <div className="charts">
            <ReactECharts option={optionPieUser} />
            <ReactECharts option={optionLineUser} />
        </div>
    );

}
export default ChartsUser;