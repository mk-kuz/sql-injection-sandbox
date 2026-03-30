package jpa.live.api;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String user,
                        @RequestParam String pass,
                        HttpSession session, Model model) {
        if (user.isEmpty() || pass.isEmpty()) {
            model.addAttribute("error", "Username and Password cannot be blank!");
            return "login";
        }
        if (user != null && pass != null) {
            String sql = "SELECT * FROM users WHERE username = '" + user +
                    "' AND password = '" + pass + "'";
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
            if (!results.isEmpty()) {
                if (checkForHack(results)){
                    model.addAttribute("error", "nice try!");
                    return "login";
                }
                session.setAttribute("loggedInUser", results.get(0).get("USERNAME"));
                return "redirect:/";
            } else {
                model.addAttribute("error", "Invalid username or password!");
            }
        }
        return "login";
    }

    private boolean checkForHack(List<Map<String, Object>> results) {
        return results.size() > 1;
    }


    @GetMapping("/")
    public String index(@RequestParam(required = false) String name,
                        HttpSession session,
                        Model model) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        if (name != null) {
            String sql = "SELECT * FROM products WHERE name = '" + name + "'";
            List<Map<String, Object>> products = jdbcTemplate.queryForList(sql);
            model.addAttribute("products", products);
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/debug")
    @ResponseBody
    public String debugTables() {
        try {
            String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'";
            List<String> tables = jdbcTemplate.queryForList(sql, String.class);

            if (tables.isEmpty()) {
                return "💀 SYSTEM ALERT: All tables have been DELETED!";
            } else {
                return "✅ DATABASE STATUS: Online. Tables found: " + String.join(", ", tables);
            }
        } catch (Exception e) {
            return "❌ ERROR: Could not connect to database: " + e.getMessage();
        }
    }


}

