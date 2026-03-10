package ma.enset.activite_pratique_2_spring_mvc.web;

import jakarta.validation.Valid;
import ma.enset.activite_pratique_2_spring_mvc.entities.Product;
import ma.enset.activite_pratique_2_spring_mvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController  {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public  String home(){
        return "redirect:/user/index";
    }
    @GetMapping("/admin/newProduct")
    public  String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "newProduct";
    }
    @GetMapping("/user/index")
    public String index(Model model) {
        List<Product> products=productRepository.findAll();
        model.addAttribute("productList", products);
        return  "products";
    }
    @PostMapping("/admin/delete")
    public  String delete(@RequestParam(name="id")Long id){
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }
    @PostMapping("/admin/saveProduct")
    public  String saveProduct(@Valid Product product, BindingResult bindingResult, Model modelt){
        if(bindingResult.hasErrors()){
            return "newProduct";
        }
        productRepository.save(product);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/editProduct")
    public String edit(@RequestParam(name="id") Long id,Model model){
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "newProduct";
    }
    @GetMapping("/NotAuthorized")
    public String NotAuthorized(){
        return "notAuthorized";
    }

}
