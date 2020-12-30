function showConfirmEmployment(text, number) {
    let r = confirm(text);
    if (r === true) {
        document.getElementById('EmploymentListForm:j_idt39:'+number+':hdnBtn').click();
    }
}

function showConfirmBabysitter(text, number) {
    let r = confirm(text);
    if (r === true) {
            document.getElementById('BabysitterListForm:j_idt39:'+number+':hdnBtn').click();
    }
}
