onload = () => {
    $('#headerDivB').text('项目详情')

    let projectId = $util.getPageParam('seeProject')
    fetchProjectInfo(projectId)
    fetchQnnreInfo(projectId)
}

const fetchProjectInfo = (id) => {
    let params = {
        id
    }
    $.ajax({
        url: API_BASE_URL + '/queryProjectList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let info = res.data[0]
            console.log(info, 'res')
            $('#projectName').text(info.projectName)
            $('#createTime').text(info.createTime)
            $('#projectDescription').text(info.projectContent)
            $('#personInCharge').text(info.personInCharge)
        }
    })
}

const fetchQnnreInfo = (selectedProjectId) => {
    $.ajax({
        url: API_BASE_URL + '/getQnnresExcludeDeletedQnnre',
        type: "POST",
        data: JSON.stringify({projectId: selectedProjectId}),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let index = 0
            console.log(res)
            res.data.forEach(qnnre => {
                    index++;
                    addRow(index, qnnre)
                }
            )
        }
    })
}

const addRow = (index, qnnre) => {
    let tbody = $('table > tbody');

    let ele = `
    <tr>
        <td>${index}</td>
        <td>${qnnre.name}</td>
        <td>${qnnre.startTime}</td>
        <td>
            <button type="button" class="btn btn-link" onclick="handlePreviewQnnre('${qnnre.id}')">预览</button>
            <button type="button" class="btn btn-link" onclick="handlePublishQnnre('${qnnre.id}')">发布</button>
            <button type="button" class="btn btn-link btn-red" onclick="handleDeleteQnnre('${qnnre.id}')">删除</button>
            <button type="button" class="btn btn-link btn-red">统计</button>
        </td>
    </tr>`
    tbody.append(ele);
}

const handlePreviewQnnre = (qnnreId) => {
    $util.setPageParam('selectedQnnreId', qnnreId)
    location.href = '/pages/qnnrePreview/index.html'
}

const handlePublishQnnre = (qnnreId) => {
    // $util.setPageParam('selectedQnnreId', qnnreId)
    // location.href = '/pages/qnnrePreview/index.html'
    let param = {qnnreId: qnnreId}
    $.ajax({
        url: API_BASE_URL + '/publishQnnre',
        type: "POST",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            alert(res.message)
        }
    })

}

const handleDeleteQnnre = (qnnreId) => {
    $.ajax({
        url: '/deleteQnnre',
        type: "POST",
        data: JSON.stringify({qnnreId: qnnreId}),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            alert(res.message)
            location.href = '/pages/seeProject/index.html'
        }
    })
}