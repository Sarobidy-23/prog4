const dropdowns = document.querySelectorAll(".dropdown");

for (let drop of dropdowns) {
    const dropBtn = drop.querySelector(".dropbtn");
    dropBtn.addEventListener("click",(event) => {
        event.preventDefault();
        drop.querySelector(".dropdown-menu").classList.toggle("show");
    })
}