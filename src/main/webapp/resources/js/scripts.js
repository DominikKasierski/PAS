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

function checkLogin(login, message) {
    // TODO: SPRAWDZIC CZY NIE JEST PUSTY, SPRAWDZIC CZY MA MINIMUM 2 ZNAKI
    return false;
    // if (poprawny) {
    //     document.getElementById('loginDiv').innerHTML = "";
    // } else {
    //     document.getElementById('loginDiv').innerHTML = message;
    //     return false;
    // }
}

function checkName(name, message) {
    // TODO:SPRAWDZIC CZY NIE JEST PUSTY, SPRAWDZIC CZY MA MINIMUM 2 ZNAKI

    if (poprawny) {
        document.getElementById('nameDiv').innerHTML = "";
    } else {
        document.getElementById('nameDiv').innerHTML = message;
        return false;
    }
}

function checkSurname(surname, message) {
    // TODO:SPRAWDZIC CZY NIE JEST PUSTY, SPRAWDZIC CZY MA MINIMUM 2 ZNAKI

    if (poprawny) {
        document.getElementById('surnameDiv').innerHTML = "";
    } else {
        document.getElementById('surnameDiv').innerHTML = message;
        return false;
    }
}

function checkNumberOfChildren(number, message) {
    // TODO:SPRAWDZIC CZY JEST LICZBA I CZY DODATNIA

    if (poprawny) {
        document.getElementById('numberDiv').innerHTML = "";
    } else {
        document.getElementById('numberDiv').innerHTML = message;
        return false;
    }
}

function checkAgeOfTheYoungestChild(age, message) {
    // TODO:SPRAWDZIC CZY JEST LICZBA I CZY Z ZAKRESU 0 - 15

    if (poprawny) {
        document.getElementById('ageDiv').innerHTML = "";
    } else {
        document.getElementById('ageDiv').innerHTML = message;
        return false;
    }
}

function checkAdminSuperUser() {
    checkLogin(document.getElementById('j_idt37:login'), 'dupa');
    // checkName(document.getElementById('nameDiv'));
    // checkSurname(document.getElementById('surnameDiv'));
    return true;
}

function checkClient() {
    return false;
    // checkLogin(login);
    // checkName(imie);
    // checkSurname(nazwisko);
    // checkNumberOfChildren();
    // checkAgeOfTheYoungestChild();
    //jesli wszystko git to sie zwraca true, jak nie to false
}
