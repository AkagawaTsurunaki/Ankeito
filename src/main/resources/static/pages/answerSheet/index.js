let questionnaireTitle
let questionnaireDescription
let questionnaireId
const problem = []
let responseSheetDTO

onload = () => {
  let selectedQnnreId = $util.getPageParam('selectedQnnreId')
  let addResponseSheetParam = {
    qnnreId : selectedQnnreId
  }

  $.ajax({
    url: '/response/createEmptyResponseSheet', // 接口地址
    type: 'POST',
    data: JSON.stringify(addResponseSheetParam),
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
          questionDTOList: [{
            question: {
              id,
              qnnreId,
              content,
              required,
              type,
            },
            optionList: [{
              option: {
                id,
                questionId,
                content,
                qnnreId,
              },
              selected
            }],
          }] = res.data.qnnreDTO.questionDTOList
        }
        questionnaireId = responseSheetDTO.qnnreDTO.qnnre.id
        questionnaireTitle = responseSheetDTO.qnnreDTO.qnnre.name
        questionnaireDescription = responseSheetDTO.qnnreDTO.qnnre.description
        $('#qnnreTitle').text(questionnaireTitle)
        $('#qnnreDescription').text(questionnaireDescription)
        $('#respondentName').text(responseSheetDTO.responseSheet.respondentName)
        load()
      } else {
        alert(res.message)
      }
    }
  });
}

const load = () => {
  responseSheetDTO.qnnreDTO.questionDTOList.forEach(questionDTO => {
    switch (questionDTO.question.type) {
      case 'SINGLE_CHOICE_QUESTION':
        singleChoice(questionDTO)
        break;
      case 'MULTIPLE_CHOICE_QUESTION':
        singleMultiple(questionDTO)
        break;
      default:
        break;
    }
  })
}

/**
 * 增加一个单选题(已作答)
 * @param questionDTO
 * @author AkagawaTsurunaki
 */
const singleChoice = (questionDTO) => {
  let question = questionDTO.question
  let ele = `
    <div class="question" id="question${question.id}" data-type="1" data-problemIndex="${question.id}">
        <div class="top">
            <span class="question-title" id="questionTitle">${question.content}</span>
            <span class="must-answer" id="mustAnswer">${question.required === 'REQUIRED' ? '必答题' : '非必答题'}</span>
        </div>
        <div class="bottom2" style="display: inline;" >
        
        </div>
    </div>`

  $('#problem').append(ele)
  questionDTO.optionList.map(optionDTO => {
    $(`#question${question.id} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="radio-inline">
          <input type="radio" name="radio-${question.id}" ${optionDTO.selected ? 'checked' : ''}>${optionDTO.option.content}
        </label>
      </div>
    `)
  })
}

/**
 * 增加多选
 * @author AkagawaTsurunaki
 *
 */
const singleMultiple = (questionDTO) => {
  let question = questionDTO.question
  let ele = `
    <div class="question" id="question${question.id}" data-type="2" data-problemIndex="${question.id}">
        <div class="top">
            <span class="question-title" id="questionTitle">${question.content}</span>
            <span class="must-answer" id="mustAnswer">${question.required === 'REQUIRED' ? '必答题' : '非必答题'}</span>
        </div>
        <div class="bottom2" style="display: inline;">
        
        </div>
    </div>`

  $('#problem').append(ele)

  questionDTO.optionList.map(optionDTO => {
    $(`#question${question.id} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="checkbox-inline">
          <input type="checkbox" name="checkbox-${question.id}" ${optionDTO.selected ? 'checked' : ''}>${optionDTO.option.content}
        </label>
      </div>
    `)
  })
}


const handleSubmmit = () => {
  alert("提交成功")
}


// onload = () => {
//   $('#problem').append(`
//     <div class="question" id="question1" data-type="1" data-problemIndex="1">
//       <div class="top">
//         <span class="question-title" id="questionTitle">1.单选题</span>
//         <span class="must-answer" id="mustAnswer">必答题</span>
//       </div>
//       <div class="bottom">
//         <div style="display: flex; align-items: center; margin-bottom: 3px;">
//           <label class="radio-inline">
//             <input type="radio" name="chooseTerm">选项1
//           </label>
//         </div>
//         <div style="display: flex; align-items: center; margin-bottom: 3px;">
//           <label class="radio-inline">
//             <input type="radio" name="chooseTerm">选项2
//           </label>
//         </div>
//         <div style="display: flex; align-items: center; margin-bottom: 3px;">
//           <label class="radio-inline">
//             <input type="radio" name="chooseTerm">选项3
//           </label>
//         </div>
//         <div style="display: flex; align-items: center; margin-bottom: 3px;">
//           <label class="radio-inline">
//             <input type="radio" name="chooseTerm">选项4
//           </label>
//         </div>
//       </div>
//     </div>
//   `)
//   $('#problem').append(`
//     <div class="question" id="question1" data-type="1" data-problemIndex="1">
//       <div class="top">
//         <span class="question-title" id="questionTitle">2.多选题</span>
//         <span class="must-answer" id="mustAnswer">必答题</span>
//       </div>
//       <div class="bottom">
//         <div style="display: flex; align-items: center; margin-bottom: 3px;">
//           <label class="checkbox-inline">
//             <input type="checkbox" name="chooseTerm">选项1
//           </label>
//         </div>
//         <div style="display: flex; align-items: center; margin-bottom: 3px;">
//           <label class="checkbox-inline">
//             <input type="checkbox" name="chooseTerm">选项2
//           </label>
//         </div>
//         <div style="display: flex; align-items: center; margin-bottom: 3px;">
//           <label class="checkbox-inline">
//             <input type="checkbox" name="chooseTerm">选项3
//           </label>
//         </div>
//         <div style="display: flex; align-items: center; margin-bottom: 3px;">
//           <label class="checkbox-inline">
//             <input type="checkbox" name="chooseTerm">选项4
//           </label>
//         </div>
//       </div>
//     </div>
//   `)
//   $('#problem').append(`
//     <div class="question" id="question1" data-type="1" data-problemIndex="1">
//       <div class="top">
//         <span class="question-title" id="questionTitle">3.填空题</span>
//         <span class="must-answer" id="mustAnswer">必答题</span>
//       </div>
//       <div class="bottom">
//         <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
//     </div>
//   `)
//   $('#problem').append(`
//     <div class="question" id="question1" data-type="1" data-problemIndex="1">
//       <div class="top">
//         <span class="question-title" id="questionTitle">4.矩阵题</span>
//         <span class="must-answer" id="mustAnswer">必答题</span>
//       </div>
//       <div class="bottom">
//         <table class="table">
//           <thead>
//             <tr>
//               <th></th>
//               <th>选项1</th>
//               <th>选项2</th>
//               <th>选项3</th>
//             </tr>
//           </thead>
//           <tbody>
//             <tr>
//               <td>标题1</td>
//               <td><input type="radio" name="chooseTerm1" /></td>
//               <td><input type="radio" name="chooseTerm1" /></td>
//               <td><input type="radio" name="chooseTerm1" /></td>
//             </tr>
//             <tr>
//               <td>标题2</td>
//               <td><input type="radio" name="chooseTerm2" /></td>
//               <td><input type="radio" name="chooseTerm2" /></td>
//               <td><input type="radio" name="chooseTerm2" /></td>
//             </tr>
//           </tbody>
//         </table>
//       </div>
//     </div>
//   `)
//   $('#problem').append(`
//     <div class="question" id="question1" data-type="1" data-problemIndex="1">
//       <div class="top">
//         <span class="question-title" id="questionTitle">5.量表题</span>
//         <span class="must-answer" id="mustAnswer">必答题</span>
//       </div>
//       <div class="bottom" style="display: flex; align-items: center; justify-content: space-between;">
//         <div>很满意</div>
//         <div>
//           <label class="radio-inline">
//             <input type="radio" name="fraction" />5
//           </label>
//         </div>
//         <div>
//           <label class="radio-inline">
//             <input type="radio" name="fraction" />4
//           </label>
//         </div>
//         <div>
//           <label class="radio-inline">
//             <input type="radio" name="fraction" />3
//           </label>
//         </div>
//         <div>
//           <label class="radio-inline">
//             <input type="radio" name="fraction" />2
//           </label>
//         </div>
//         <div>
//           <label class="radio-inline">
//             <input type="radio" name="fraction" />1
//           </label>
//         </div>
//         <div>很不满意</div>
//       </div>
//     </div>
//   `)
// }
