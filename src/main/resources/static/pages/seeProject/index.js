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
            res.data.forEach(qnnre => {
                    addRow(index, qnnre)
                    index++;
                }
            )
        }
    })
}

const addRow = (index, qnnre) => {
    let tbody = $('table > tbody');
    let tr = $('<tr>')
        .append($('<td>').text(index)
            .append($('<td>').text(qnnre.name))
            .append($('<td>').text(qnnre.startTime))
            .append($('<td>')
                .append($('<button>').prop('type', 'button').addClass('btn btn-link').text('预览')).on('click', function () {handlePreviewQnnre(qnnre.id)})
                .append($('<button>').prop('type', 'button').addClass('btn btn-link').text('发布')).on('click', function () {})
                .append($('<button>').prop('type', 'button').addClass('btn btn-link btn-red').text('删除')).on('click', function () {handleDeleteQnnre(qnnre.id)})
                .append($('<button>').prop('type', 'button').addClass('btn btn-link btn-red').text('统计'))
            ));

    tbody.append(tr);
}

const handlePreviewQnnre = (qnnreId) => {
    $util.setPageParam('selectedQnnreId', qnnreId)
    location.href = '/pages/qnnrePreview/index.html'
}

const handlePublishQnnre = (qnnreId) => {
    $util.setPageParam('selectedQnnreId', qnnreId)
    location.href = '/pages/qnnrePreview/index.html'
}

const handleDeleteQnnre = (qnnreId) => {
    $.ajax({
        url: API_BASE_URL + '/deleteQnnre',
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