let questionnaireTitle
let questionnaireDescription
let questionnaireId
const problem = []
let responseSheetDTO

onload = () => {
    let queryResponseSheetParam = {
        responseSheetId: $util.getPageParam('selectedResponseSheetId')
    }

    $.ajax({
        url: '/response/getResponseSheetDetail', // 接口地址
        type: 'POST',
        data: JSON.stringify(queryResponseSheetParam),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === '666') {
                responseSheetDTO = {
                    responseSheet: res.data.responseSheet,
                    qnnreDTO: res.data.qnnreDTO
                }
                responseSheetDTO.responseSheet = {
                    id: res.data.responseSheet.id,
                    qnnreId: res.data.responseSheet.qnnreId,
                    qnnreName: res.data.responseSheet.qnnreName,
                    respondentId: res.data.responseSheet.respondentId,
                    respondentName: res.data.responseSheet.respondentName,
                    finishedTime: res.data.responseSheet.finishedTime,
                }
                responseSheetDTO.qnnreDTO = {
                    qnnre: res.data.qnnreDTO.qnnre,
                    questionDTOList: res.data.qnnreDTO.questionDTOList
                }
                questionnaireId = responseSheetDTO.qnnreDTO.qnnre.id
                questionnaireTitle = responseSheetDTO.qnnreDTO.qnnre.name
                questionnaireDescription = responseSheetDTO.qnnreDTO.qnnre.description
                $('#qnnreTitle').text(questionnaireTitle)
                $('#qnnreDescription').text(questionnaireDescription)
                $('#respondentName').text(responseSheetDTO.responseSheet.respondentName)
            } else {
                alert(res.message)
            }
        }
    });
}