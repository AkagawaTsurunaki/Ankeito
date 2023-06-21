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

const load = () => {

    responseSheetDTO.qnnreDTO.questionDTOList.forEach(questionDTO => {
        let question = questionDTO.question
        let ele
        switch (question.type) {
            case 'SINGLE_CHOICE_QUESTION':
                ele = singleChoice(question)
                break;
            case  'MULTIPLE_CHOICE_QUESTION':
                ele = singleChoice(question)
                break;
            default:
                break;
        }

    })

}

const singleChoice = (questionDTO) => {
    let question = questionDTO.question
    let ele = `
    <div class="question" id="question${question.id}" data-type="1" data-problemIndex="${question.id}">
        <div class="top">
            <span class="question-title" id="questionTitle">${question.content}</span>
            <span class="must-answer" id="mustAnswer">${question.required === 'REQUIRED' ? '必答题' : '非必答题'}</span>
        </div>
        <div class="bottom2" style="display: inline;">
        
        </div>
    </div>
    `
    $('#problem').append(ele)
    questionDTO.optionList.map(item => {
        $(`#question${problemIndex} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="radio-inline">
          <input type="radio">${item.chooseTerm ? item.chooseTerm : ''}
        </label>
      </div>
    `)
    })


}

const handleAddSingleChoice = () => {
    problem.type = 'SINGLE_CHOICE_QUESTION'
    return `
    <div class="question" id="question${problem.length}" data-type="1" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="单选题目" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="option" id="option">
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" placeholder="选项【单选】" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <span class="option-del" onclick="singleChoiceDelOption(${problem.length}, 0)">删除</span>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onclick="singleChoiceAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onclick="singleChoiceEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none;">
        
      </div>
    </div>
  `
}
