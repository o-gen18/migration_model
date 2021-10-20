//package exercise.migration_model.controller;
//
//import exercise.migration_model.model.Migration;
//import exercise.migration_model.repository.MigrationRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/migration")
//public class MigrationController {
//    private MigrationRepository migrationRepository;
//
//    public MigrationController(MigrationRepository migrationRepository) {
//        this.migrationRepository = migrationRepository;
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<Migration> addMigration(@RequestBody Migration migration) {
//        try {
//            migration.setId(UUID.randomUUID());
//            Migration migrationSaved = migrationRepository.save(migration);
//            migrationSaved.setMigrationState(Migration.MigrationState.NOT_STARTED);
//            return new ResponseEntity<Migration>(migration, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Migration> modifyMigration(@PathVariable("id") UUID id,
//                                                     @RequestBody Migration migration) {
//        Optional<Migration> migrationOptional = migrationRepository.findById(id);
//        if (migrationOptional.isPresent()) {
//            Migration migrationPersisted = migrationOptional.get();
//            migrationPersisted.setSelectedMountPoints(migration.getSelectedMountPoints());
//            migrationPersisted.setSource(migration.getSource());
//            migrationPersisted.setTargetCloud(migration.getTargetCloud());
//            try {
//                return new ResponseEntity<Migration>(migrationRepository.save(migrationPersisted),
//                        HttpStatus.OK);
//            } catch (Exception e) {
//                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> removeMigration(@PathVariable("id") UUID id) {
//        try {
//            migrationRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
