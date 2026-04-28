package org.example.website.controller;


import org.example.website.model.Product;
import org.example.website.model.ProductCategory;
import org.example.website.model.TextEntry;
import org.example.website.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class Main {
    private final ProductService productService;
    private final TextService textService;

    public Main(ProductService productService, OCompaniesService oCompaniesService, TextService textService, TextService textService1, PortfolioService portfolioService) {
        this.productService = productService;
        this.textService = textService1;
    }


    @GetMapping("/login")
    public String admin(Model model) {
        return "login";
    }

    @GetMapping("/addInfo")
    public String addInfo(Model model) {
        return "AddInfo";
    }

    @GetMapping("/")
    public String main(Model model) {
        ProductCategory category = ProductCategory.XITPRODAG;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        List<TextEntry> textControllers = textService.lisText();
        model.addAttribute("textControllers", textControllers);

        return "main";
    }

    @GetMapping("/WroughtIronGates")
    public String wroughtIronGates() {
        return "WroughtIronGates";
    }
    @GetMapping("/ForgedPavilion")
    public String forgedCalitca2() {
        return "ForgedPavilion";
    }
    @GetMapping("/Katalog")
    public String katalog() {
        return "Katalog";
    }
    @GetMapping("/CreateProduct")
    public String createProduct() {
        return "CreateProduct";
    }
    @GetMapping("/WholesaleBuyer")
    public String wholesaleBuyer() {
        return "WholesaleBuyer2";
    }
    @GetMapping("/KovkaNaZakaz")
    public String kovkaNaZakaz(){
        return "KovkaNaZakaz2";
    }
    @GetMapping("/Contacts")
    public String contacts() {
        return "Contacts";
    }

    @GetMapping("/RitualStoli")
    public String ritualStoli(Model model) {
        ProductCategory category = ProductCategory.RITUALSTOLI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "RitualStoli";
    }
    @GetMapping("/ScameikaRitual")
    public String scameikaRitual(Model model) {
        ProductCategory category = ProductCategory.RITUALSKAMEIKI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "ScameikaRitual";
    }

    @GetMapping("/RitualOgragdenia")
    public String ritualOgragdenia(Model model) {
        ProductCategory category = ProductCategory.RITUALNIAIZDELIA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "RitualOgragdenia";
    }
    @GetMapping("/RitualKresti")
    public String ritualKresti(Model model) {
        ProductCategory category = ProductCategory.RITUALKRESTI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "RitualKresti";
    }

    @GetMapping("/SaleKatalog")
    public String saleKatalog(Model model) {
        ProductCategory category = ProductCategory.SALEKATALOG;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "SaleKatalog";

    }

    @GetMapping("/VorotaKatalog")
    public String vorotaKatalog2(Model model) {
        ProductCategory category = ProductCategory.KOVANVOROTA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "VorotaKatalog";

    }
    @GetMapping("/KalitkaKatalog")
    public String forgedCalitca(Model model) {
        ProductCategory category = ProductCategory.KOVANKALITKA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "KalitkaKatalog";
    }
    @GetMapping("/ZaborKatalog")
    public String zaborKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANZABOR;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "ZaborKatalog";
    }
    @GetMapping("/PerilaKatalog")
    public String perilaKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANPERILA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "PerilaKatalog";
    }
    @GetMapping("/ReshotkaKatalog")
    public String reshotkaKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANREHOTKA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "ReshotkaKatalog";
    }
    @GetMapping("/BesedkaKatalog")
    public String besedkaKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANBESEDKA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "BesedkaKatalog";
    }
    @GetMapping("/MostiKatalog")
    public String mostiKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANMOSTI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "MostiKatalog";
    }
    @GetMapping("/KaheliKatalog")
    public String kaheliKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANKAHELI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "KaheliKatalog";
    }
    @GetMapping("/MongaliKatalog")
    public String mongaliKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANMONGALI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "MongaliKatalog";
    }
    @GetMapping("/SkameikiKatalog")
    public String skameikiKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVAMSKAMEIKI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "SkameikiKatalog";
    }
    @GetMapping("/YrbanKatalog")
    public String yrbanKatalog(Model model) {
        ProductCategory category = ProductCategory.URBAN;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "YrbanKatalog";
    }
    @GetMapping("/ChugunSkameyki")
    public String chugunSkameyki(Model model) {
        ProductCategory category = ProductCategory.CHYGUNSKAMEYKI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "ChugunSkameyki";
    }
    @GetMapping("/SadovStoli")
    public String sadovStoli(Model model) {
        ProductCategory category = ProductCategory.SADOVIESTOLI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "SadovStoli";
    }
    @GetMapping("/NaboriKatalog")
    public String naboriKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANNABORI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "NaboriKatalog";
    }
    @GetMapping("/MebelLoft")
    public String mebelLoft(Model model) {
        ProductCategory category = ProductCategory.LOFT;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "MebelLoft";
    }
    @GetMapping("/UlishnieYrni")
    public String ulishnieYrni(Model model) {
        ProductCategory category = ProductCategory.YLICHNIEYRNI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "UlishnieYrni";
    }
    @GetMapping("/KonteinerTBO")
    public String konteinerTBO(Model model) {
        ProductCategory category = ProductCategory.KONTEYNERITBO;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "KonteinerTBO";
    }
    @GetMapping("/FonarStolb")
    public String fonarStolb(Model model) {
        ProductCategory category = ProductCategory.FONARNIESTILBI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "FonarStolb";
    }
    @GetMapping("/MebelKatalog")
    public String mebelKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANMEBEL;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "MebelKatalog";
    }
    @GetMapping("/OgragdeniaKatalog")
    public String ogragdeniaKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANOGRAGDENIA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "OgragdeniaKatalog";
    }
    @GetMapping("/RitualIzdeliaKatalog")
    public String ritualIzdeliaKatalog(Model model) {
        ProductCategory category1 = ProductCategory.RITUALNIAIZDELIA;
        ProductCategory category2 = ProductCategory.RITUALKRESTI;
        ProductCategory category3 = ProductCategory.RITUALSKAMEIKI;
        ProductCategory category4 = ProductCategory.RITUALSTOLI;


        // Получаем продукты для первой категории
        List<Product> productsForCategory1 = productService.getProductsByCategory(category1);
        model.addAttribute("products1", productsForCategory1);
        model.addAttribute("category1", category1);

        // Получаем продукты для второй категории
        List<Product> productsForCategory2 = productService.getProductsByCategory(category2);
        model.addAttribute("products2", productsForCategory2);
        model.addAttribute("category2", category2);

        // Получаем продукты для третьей категории
        List<Product> productsForCategory3 = productService.getProductsByCategory(category3);
        model.addAttribute("products3", productsForCategory3);
        model.addAttribute("category3", category3);

        // Получаем продукты для четвертой категории
        List<Product> productsForCategory4 = productService.getProductsByCategory(category4);
        model.addAttribute("products4", productsForCategory4);
        model.addAttribute("category4", category4);

        return "RitualIzdeliaKatalog";
    }
    @GetMapping("/KozirkiiNavesiKatalog")
    public String kozirkiiNavesiKatalog(Model model) {
        ProductCategory category = ProductCategory.KOVANKOZIREKINAVES;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "KozirkiiNavesiKatalog";
    }
    @GetMapping("/AlementiKovkiKatalog")
    public String alementiKovkiKatalog(Model model) {
        ProductCategory category = ProductCategory.ILEMENTKOVKI;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "AlementiKovkiKatalog";
    }
    @GetMapping("/TableDoma")
    public String tableDoma(Model model) {
        ProductCategory category = ProductCategory.TABLICKANADOM;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "TableDoma";
    }
    @GetMapping("/OgragdenPloshadok")
    public String ogragdenPloshadok(Model model) {
        ProductCategory category = ProductCategory.OGRAGDENIEPLOSHADOK;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "OgragdenPloshadok";
    }
    @GetMapping("/RazhieIzdeliaKatalog")
    public String razhieIzdeliaKatalog(Model model) {
        ProductCategory category = ProductCategory.RAZNIAKOVANIZDELIA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "RazhieIzdeliaKatalog";
    }
    @GetMapping("/PolomernaiKraskaKatalog")
    public String polomernaiKraskaKatalog(Model model) {
        ProductCategory category = ProductCategory.POLOMERNAIKRASKA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "PolomernaiKraskaKatalog";
    }

    @GetMapping("/RezinovaiKroshkaKatalog")
    public String rezinovaiKroshkaKatalog(Model model) {
        ProductCategory category = ProductCategory.REZINIVAIKROHKA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "RezinovaiKroshkaKatalog";
    }
    @GetMapping("/ProductCard")
    public String productVorotaCard(Model model){
        ProductCategory category = ProductCategory.KOVANVOROTA;
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);

        return "ProductCard";
    }

}
