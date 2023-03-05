package monsterservice.controller;

import monsterservice.handleExceptionError.HandleExceptionError;
import monsterservice.model.Monster;
import monsterservice.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monster")
public class MonsterController {
    @Autowired
    private MonsterService monsterService;
    @GetMapping("/greeting")
    public String getGreeting(){
        return "Hi Apy!";
    }

    @PostMapping("/create")
    public  Monster postCrete(@RequestBody Monster monster){
        return monsterService.postCreateMonsterService(monster);
    }

    @GetMapping("/get-all")
    public List<Monster> getAll(){
            return  monsterService.getAllMonsterService();
    }

    @GetMapping("/get-information")
    public Optional<Monster> getInformation(@RequestHeader Integer id){
        return monsterService.InformationService(id);
    }
    @PutMapping("/update")
    public  ResponseEntity<Monster> putUpdate(@RequestBody Monster monster){
        try {
            return new ResponseEntity<>(
                    monsterService.updateMonsterByIdService(monster),
                    HttpStatus.OK);
        }catch (HandleExceptionError ex){
            return new ResponseEntity<>(new Monster(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean>  delete(@RequestHeader Integer id){
        try {
            return  new ResponseEntity<>(
                    monsterService.deleteMonsterService(id),
                    HttpStatus.OK
            );

        }catch (HandleExceptionError ex){
           return  new ResponseEntity<>(
                    false,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/attack")
    public ResponseEntity<String>  putAttack(
            @RequestHeader Integer id,@RequestHeader Integer damage){
        try {
            return new ResponseEntity<>(
                    monsterService.attackMonsterService(id, damage),
                    HttpStatus.OK);
        }catch (HandleExceptionError ex){
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
