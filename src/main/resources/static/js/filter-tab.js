document.addEventListener("DOMContentLoaded", () => {
    const filterTabBtn = document.getElementById("filter-tab-btn");
    const filterTabCloseBtn = document.getElementById("filter-tab-close-btn");

    const filterBg = document.getElementById("filter-bg-blur");
    const filterTab = document.getElementById("filter-tab");

    filterTabBtn.addEventListener("click", () => {
        filterBg.classList.remove("none");
        setTimeout(() => filterTab.classList.add("up"), 10);
    })

    filterTabCloseBtn.addEventListener("click", () => {
        filterTab.classList.remove("up");
        filterTab.addEventListener("transitionend", function handler() {
            filterBg.classList.add("none");
            filterTab.removeEventListener("transitionend", handler);
        });
    })
})