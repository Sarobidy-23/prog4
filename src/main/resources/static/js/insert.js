const inputPhone = document.querySelector("#phone");
const form = document.querySelector("#form")
let country = window.intlTelInput(inputPhone, {
    utilsScript: "/js/utils.js",
    onlyCountries: ["mg","fr","us"],
});

form.addEventListener("submit",() => {
    let countryCode = country.getSelectedCountryData().dialCode
    inputPhone.value = "+(" + countryCode + ")" + inputPhone.value.replace(/^0+/, '')
    form.submit();
})

const addPhone = () => {
    const phoneList = document.querySelector("#phoneList")
    const index = phoneList.children.length;
    const inputPhone = document.querySelector("#phone");
    const valueInput = document.createElement("input")
    valueInput.setAttribute("name",`phones[${index}].phoneWithCountry`)
    valueInput.setAttribute("readonly", "true")
    valueInput.setAttribute("value", inputPhone.value)
    phoneList.appendChild(valueInput)
    inputPhone.setAttribute("value","")
    console.log(index)
}