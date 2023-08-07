const inputPhone = document.querySelector("#phone");
const form = document.querySelector("#form")
const phoneList = document.querySelector("#phoneList")
let country = window.intlTelInput(inputPhone, {
    utilsScript: "/js/utils.js",
    onlyCountries: ["mg","fr","us"],
});

const concatPhone = () => {
    let countryCode = country.getSelectedCountryData().dialCode
    return inputPhone.value = "+(" + countryCode + ")" + inputPhone.value.replace(/^0+/, '')
}

const addPhone = () => {
    const index = phoneList.children.length;
    const valueInput = document.createElement("input")
    valueInput.setAttribute("name",`phones[${index}].phoneWithCountry`)
    valueInput.setAttribute("type","tel")
    valueInput.setAttribute("value", concatPhone())
    valueInput.setAttribute("class", "form-group col-sm-6 flex-column d-flex phone")
    phoneList.appendChild(valueInput)
    inputPhone.value = ""
    console.log(index)
}