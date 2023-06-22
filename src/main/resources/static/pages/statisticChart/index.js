onload = () => {
}


const fun = (index) => {
    let ele = `
    <div class="question" id="question${question.id}" data-type="1" data-problemIndex="${question.id}">
        <div class="top">
            <span class="question-title">第${index}题</span>
            <span class="question-title" id="questionTitle">${question.content}</span>
            <span class="must-answer" id="mustAnswer">${question.required === 'REQUIRED' ? '必答题' : '非必答题'}</span>
        </div>
        <div class="bottom2" style="display: inline;" >

        </div>
    </div>
    `

    $('#problem').append(ele)
    questionDTO.optionList.map(optionDTO => {
        $(`#question${question.id} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="radio-inline">
          <input type="radio" id="radio-${question.id}-${optionDTO.option.id}" name="radio-${question.id}" ${optionDTO.selected ? 'checked' : ''}>${optionDTO.option.content}
        </label>
      </div>
    `)
    });
}

const table = () => {

    let ele = `
    <div class="problem" id="problem">
        <table class="table table-bordered table-striped">
            <tr>
                <th>选项</th>
                <th>小计</th>
                <th>比例</th>
            </tr>
            <tr>
                <td>选项1</td>
                <td>66</td>
                <td>
                    <div class="progress-bar">
                        <div class="progress-fill" style="width: 15.07%;"></div>
                    </div>
                    15.07%
                </td>
            </tr>
            <tr>
                <td>选项2</td>
                <td>302</td>
                <td>
                    <div class="progress-bar">
                        <div class="progress-fill" style="width: 68.95%;"></div>
                    </div>
                    68.95%
                </td>
            </tr>
            <tr>
                <td>选项3</td>
                <td>70</td>
                <td>
                    <div class="progress-bar">
                        <div class="progress-fill" style="width: 15.98%;"></div>
                    </div>
                    15.98%
                </td>
            </tr>
            <tr>
                <td>本题有效填写人次</td>
                <td>438</td>
                <td></td>
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
        <div id="chartContainer" style="width: 600px; height: 400px;"></div>
    </div>
    `
}