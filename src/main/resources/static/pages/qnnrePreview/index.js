let selectedQnnreId

onload = () => {
    selectedQnnreId = $util.getPageParam('selectedQnnreId')
}

const fetchQnnre  = () => {
    $.ajax({
        url: API_BASE_URL + '/getQnnre',
        type: "POST",
        data: JSON.stringify({id: qnnreId}),
        dataType: "json",
        contentType: "application/json",
        success(res) {

        }
    })
}
