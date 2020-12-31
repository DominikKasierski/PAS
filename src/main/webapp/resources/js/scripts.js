function showConfirmEmployment(text, number) {
    let r = confirm(text);
    if (r === true) {
        document.getElementById('EmploymentListForm:j_idt39:' + number + ':hdnBtn').click();
    }
}

function showConfirmBabysitter(text, number) {
    let r = confirm(text);
    if (r === true) {
        document.getElementById('BabysitterListForm:j_idt39:' + number + ':hdnBtn').click();
    }
}

function checkLogin(login) {
    console.log(login);
}

function checkName(name) {
    console.log(name);
}

function checkSurname(surname) {
    console.log(surname);
}

function checkNumberOfChildren(number) {
    console.log(number);
}

function checkAgeOfTheYoungestChild(age) {
    console.log(age);
}

function checkAll1(login) {
    return false;
    // checkLogin(login);
    // checkName(imie);
    // checkSurname(nazwisko);
    //jesli wszystko git to sie wywoluje metoda z ukrytego, jak nie to nic
}

function checkAll2(login, name, surname, number, age) {
    // checkLogin(login);
    // checkName(imie);
    // checkSurname(nazwisko);
    // checkNumberOfChildren();
    // checkAgeOfTheYoungestChild();
    //jesli wszystko git to sie wywoluje metoda z ukrytego, jak nie to nic
}
