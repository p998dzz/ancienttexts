package lt.ancienttexts.controller.admin;

import lt.ancienttexts.domain.TabletRequest;
import lt.ancienttexts.service.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private TabletService tabletService;

    @PostMapping(value = "/tablet", produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> createTablet(@RequestBody TabletRequest tabletRequest) {
        tabletService.createTabletDetails(tabletRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tablet created successfully");
    }

    @DeleteMapping(value = "/tablet/{id}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> deleteTablet(@PathVariable Long id) {
        tabletService.deleteTabletDetails(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tablet deleted successfully");
    }

    @PutMapping(value = "/tablet/{id}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> updateTablet(@PathVariable Long id, @RequestBody TabletRequest tabletRequest) {
        tabletService.updateTabletDetails(id, tabletRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tablet updated successfully");
    }

}
