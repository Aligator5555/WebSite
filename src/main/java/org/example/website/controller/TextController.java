package org.example.website.controller;

import org.example.website.model.TextEntry;
import org.example.website.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TextController {

    private final TextService textService;

    @Autowired
    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping("/addText")
    public String addText() {
        return "AddTextOCompanies";
    }

    @GetMapping("/text/delete/{id}")
    public String deleteText(@PathVariable Long id) {
        textService.deleteText(id);
        return "redirect:/";
    }


    @PostMapping("/saveText")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveText(@RequestParam("content") String content, RedirectAttributes redirectAttributes) {
        try {
            TextEntry savedEntry = textService.saveText(content);
            redirectAttributes.addFlashAttribute("successMessage", "Текст успешно сохранён!");
            return "redirect:/"; // или другой URL после успешного сохранения
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при сохранении: " + e.getMessage());
            return "redirect:/"; // возвращаемся на страницу с формой
        }
    }

}

// DTO для приёма текста
class TextRequest {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

