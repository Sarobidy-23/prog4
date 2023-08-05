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