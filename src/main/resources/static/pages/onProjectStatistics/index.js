onload = () => {
    $('#headerUsername').text($util.getItem('userInfo').username)
    handleHeaderLoad()
    fetchResponseSheetList()
}

class ResponseSheet {
    constructor(id, qnnreId, qnnreName, respondentId, respondentName, finishedTime) {
        this.id = id;
        this.qnnreId = qnnreId;
        this.qnnreName = qnnreName;
        this.respondentId = respondentId;
        this.respondentName = respondentName;
        this.finishedTime = finishedTime;
    }
}

let responseSheet

const onClickDetail = (id) => {
    $util.setPageParam('selectedResponseSheetId', id)
    location.href = '/pages/responseSheetDetail/index.html'
}

const fetchResponseSheetList = () => {
    let selectedProjectId = $util.getPageParam('onProjectStatistics')
    $.ajax({
        url: API_BASE_URL + '/response/getResponseSheet',
        type: 'POST',
        data: JSON.stringify({
            projectId: selectedProjectId
        }),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            $('#table #tbody').html('')
            responseSheet = res.data
            responseSheet.map((item, index) => {
                $('#table #tbody').append(`
          <tr>
            <td>${index + 1}</td>
            <td>${item.qnnreName}</td>
            <td>${item.respondentName}</td>
            <td>${item.finishedTime}</td>
            <td>
              <button type="button" class="btn btn-link" onclick="onClickDetail('${item.id}')">明细</button>
            </td>
          </tr>
        `)
            })
        }
    })

    // let responseSheetDTO = {
    //     responseSheet: {
    //         id:,
    //         qnnreId:,
    //         qnnreName:,
    //         respondentId:,
    //         respondentName:,
    //         finishedTime: ;
    //     },
    //     qnnreDTO: {
    //         qnnre: {
    //             id:,
    //             projectId:,
    //             name:,
    //             description:,
    //             startTime:,
    //             stopTime:,
    //             qnnreStatus:
    //         },
    //         questionDTOList: [{
    //             question: {
    //                 id:,
    //                 qnnreId:,
    //                 content:,
    //                 required:,
    //                 type:
    //             },
    //             optionList: [{
    //                 option: {
    //                     id:,
    //                     questionId:,
    //                     content:
    //                 },
    //                 selected:
    //             }]
    //         }]
    //     }
    // }
}