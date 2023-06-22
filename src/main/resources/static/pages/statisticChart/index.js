let questionStatisticDTOList

onload = () => {
    $util.getPageParam('selectedQnnreId')
    $util.getPageParam('selectedQnnreId')
    let param = {
        qnnreId: $util.getPageParam('selectedQnnreId')
    }

    $.ajax({
        url: '/statistic/getQuestionStatistic', // 接口地址
        type: 'POST',
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === '666') {
                questionStatisticDTOList = [
                    {
                        questionId,
                        questionName,
                        questionCount,
                        optionList: {
                            optionId,
                            optionContent,
                            percent,
                            count,
                        }
                    }
                ] = res.data

                questionStatisticDTOList.forEach(
                    questionStatisticDTO => {
                        table(questionStatisticDTO)
                        pie(questionStatisticDTO)
                        ring(questionStatisticDTO)
                        bar(questionStatisticDTO)
                        tiao(questionStatisticDTO)
                        line(questionStatisticDTO)
                    }
                )
            }
        }
    });

}

const table = (questionStatisticDTO) => {
    let questionId = questionStatisticDTO.questionId

    let ele = `
    <div class="problem" id="problem${questionId}">
        <div id="tableStatistic${questionId}">
        <table id="tableQuestion${questionId}" class="table table-bordered table-striped">
            <tr id="tr${questionId}">
                <th>选项</th>
                <th>小计</th>
                <th>比例</th>
            </tr>
        </table>
        </div>
        <div id="pieStatistic${questionId}" class="chartContainer" style="width: 600px; height: 400px; "></div>
        <div id="ringStatistic${questionId}" class="chartContainer" style="width: 600px; height: 400px; "></div>
        <div id="barStatistic${questionId}" class="chartContainer" style="width: 600px; height: 400px; "></div>
        <div id="yBarStatistic${questionId}" class="chartContainer" style="width: 600px; height: 400px;"></div>
        <div id="lineStatistic${questionId}" class="chartContainer" style="width: 600px; height: 400px;"></div>
        <div style="text-align: right">
            <button type="button" class="btn btn-default">表格</button>
            <button type="button" class="btn btn-default">饼状</button>
            <button type="button" class="btn btn-default">圆环</button>
            <button type="button" class="btn btn-default">柱状</button>
            <button type="button" class="btn btn-default">条形</button>
            <button type="button" class="btn btn-default">折线</button>
        </div>
    </div>
    `

    $(`#problem`).append(ele)

    questionStatisticDTO.optionList.forEach(
        option => {
            let ele =
                `
            <tr>
                <td>${option.optionContent}</td>
                <td>${option.count}</td>
                <td>
                    <div class="progress-bar">
                        <div class="progress-fill" style="width: ${option.percent * 100}%;"></div>
                    </div>
                    ${option.percent * 100}%
                </td>
            </tr>
            `
            $(`#tr${questionId}`).after(ele)
        }
    )

    let questionCountEle = `
            <tr>
                <td>本题有效填写人次</td>
                <td>${questionStatisticDTO.questionCount}</td>
                <td></td>
            </tr>
    `
    $(`#tableQuestion${questionId} tr:last`).after(questionCountEle)


}

const pie = (questionStatisticDTO) => {
    let questionId = questionStatisticDTO.questionId
    let chartDom = document.getElementById(`pieStatistic${questionId}`)
    let myChart = echarts.init(chartDom);
    let option;

    let data = []
    questionStatisticDTO.optionList.forEach(
        innerOption => {
            data.push(
                {
                    value: innerOption.count,
                    name: innerOption.optionContent
                }
            )
        }
    )

    option = {
        title: {
            text: `${questionStatisticDTO.questionName}`,
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
                type: 'pie',
                radius: '50%',
                data: data,
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

    option && myChart.setOption(option);
}

const ring = (questionStatisticDTO) => {
    let questionId = questionStatisticDTO.questionId
    let chartDom = document.getElementById(`ringStatistic${questionId}`);
    let myChart = echarts.init(chartDom);
    let option;

    let data = []
    questionStatisticDTO.optionList.forEach(
        innerOption => {
            data.push(
                {
                    value: innerOption.count,
                    name: innerOption.optionContent
                }
            )
        }
    )

    option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: data
            }
        ]
    };
    option && myChart.setOption(option);
}

const bar = (questionStatisticDTO) =>{
    let questionId = questionStatisticDTO.questionId
    let chartDom = document.getElementById(`barStatistic${questionId}`);
    let myChart = echarts.init(chartDom);
    let option;

    let dataX = []
    let dataY = []
    questionStatisticDTO.optionList.forEach(
        innerOption => {
            dataX.push(innerOption.optionContent)
            dataY.push(innerOption.count)
        }
    )

    option = {
        xAxis: {
            type: 'category',
            data: dataX
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: dataY,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }
        ]
    };

    option && myChart.setOption(option);
}

const tiao = (questionStatisticDTO) =>{
    let questionId = questionStatisticDTO.questionId
    let chartDom = document.getElementById(`yBarStatistic${questionId}`);
    let myChart = echarts.init(chartDom);
    let option;

    let dataX = []
    let dataY = []
    questionStatisticDTO.optionList.forEach(
        innerOption => {
            dataX.push(innerOption.optionContent)
            dataY.push(innerOption.count)
        }
    )

    option = {
        yAxis: {
            type: 'category',
            data: dataX
        },
        xAxis: {
            type: 'value'
        },
        series: [
            {
                data: dataY,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }
        ]
    };

    option && myChart.setOption(option);
}

const line = (questionStatisticDTO) => {
    let questionId = questionStatisticDTO.questionId
    let chartDom = document.getElementById(`lineStatistic${questionId}`);
    let myChart = echarts.init(chartDom);
    let option;
    let dataX = []
    let dataY = []
    questionStatisticDTO.optionList.forEach(
        innerOption => {
            dataX.push(innerOption.optionContent)
            dataY.push(innerOption.count)
        }
    )
    option = {
        xAxis: {
            type: 'category',
            data: dataX
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: dataY,
                type: 'line'
            }
        ]
    };

    option && myChart.setOption(option);

}