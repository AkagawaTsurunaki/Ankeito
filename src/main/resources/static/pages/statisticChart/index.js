onload = () => {
    alert("加载成")
    var myChart = echarts.init(document.getElementById('testChart'));
    let option = {
        xAxis: {
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {},
        series: [
            {
                type: 'bar',
                data: [23, 24, 18, 25, 27, 28, 25]
            }
        ]
    };
    myChart.setOption(option)
}