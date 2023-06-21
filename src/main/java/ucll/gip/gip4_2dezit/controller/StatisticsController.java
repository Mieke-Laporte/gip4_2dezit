package ucll.gip.gip4_2dezit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucll.gip.gip4_2dezit.service.StatisticsService;

@RestController
@RequestMapping("admin/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping()
    public ResponseEntity<Object> getAmountOfPublishers(){
        try {
            int res = statisticsService.getAmountOfPublishers();
            return ResponseEntity.ok(res);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
