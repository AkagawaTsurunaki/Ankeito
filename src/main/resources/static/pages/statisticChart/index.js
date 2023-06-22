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
                    }
                )
            }
            alert(res.message)
        }
    });

}

// const fun = (questionId, questionName) => {
//     let ele = `
//     <div class="question" id="question${questionId}" data-type="1" data-problemIndex="${questionId}">
//         <div class="top">
//             <span class="question-title">第${questionId + 1}题</span>
//             <span class="question-title" id="questionTitle">${questionName}</span>
//         </div>
//         <div class="bottom2" style="display: inline;" >
//
//         </div>
//     </div>
//     `
//
//     $('#problem').append(ele)
//     questionDTO.optionList.map(optionDTO => {
//         $(`#question${question.id} .bottom2`).append(`
//       <div style="display: flex; align-items: center;">
//         <label class="radio-inline">
//           <input type="radio" id="radio-${question.id}-${optionDTO.option.id}" name="radio-${question.id}" ${optionDTO.selected ? 'checked' : ''}>${optionDTO.option.content}
//         </label>
//       </div>
//     `)
//     });
// }

const table = (questionStatisticDTO) => {
    let questionId = questionStatisticDTO.questionId

    let ele = `
    <div class="problem" id="problem${questionId}">
        <table id="table-question-${questionId}" class="table table-bordered table-striped">
            <tr id="tr${questionId}">
                <th>选项</th>
                <th>小计</th>
                <th>比例</th>
            </tr>
        </table>
        <div style="text-align: right">
            <button type="button" class="btn btn-default">表格</button>
            <button type="button" class="btn btn-default">饼状</button>
            <button type="button" class="btn btn-default">圆环</button>
            <button type="button" class="btn btn-default">柱状</button>
            <button type="button" class="btn btn-default">条形</button>
            <button type="button" class="btn btn-default">折线</button>
        </div>
<!--        <div id="chartContainer" style="width: 600px; height: 400px;"></div>-->
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
    $(`#table-question-${questionId} tr:last`).after(questionCountEle)



}