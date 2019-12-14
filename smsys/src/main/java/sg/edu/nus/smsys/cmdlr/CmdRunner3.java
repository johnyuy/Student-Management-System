package sg.edu.nus.smsys.cmdlr;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import sg.edu.nus.smsys.models.Schedule;
import sg.edu.nus.smsys.repository.CourseClassRepository;
import sg.edu.nus.smsys.repository.LecturerRepository;
import sg.edu.nus.smsys.repository.ScheduleRepository;
import sg.edu.nus.smsys.repository.SubjectRepository;
import sg.edu.nus.smsys.service.UserService;

@Component
@Order(3)
public class CmdRunner3 implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CmdRunner3.class);
	@Autowired
	UserService us;
	@Autowired
	ScheduleRepository schrepo;
	@Autowired
	LecturerRepository lrepo;
	@Autowired
	CourseClassRepository crepo;
	@Autowired
	SubjectRepository subrepo;
	
	@Override
    public void run(String[] args) {
        log.info("In CmdRunner3");   
        log.info("Create Schdule");   
        // Month of August 2019
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 1),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 2),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 3),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 4),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 5),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 6),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 7),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 8),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 9),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 10),lrepo.findByStaffId(50003),subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 11),lrepo.findByStaffId(50003),subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 12),lrepo.findByStaffId(50003),subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 13),lrepo.findByStaffId(50003),subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 14),lrepo.findByStaffId(50003),subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 15),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 16),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 17),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 18),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 19),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 20),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 21),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 22),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 23),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 24),lrepo.findByStaffId(50004),subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 25),lrepo.findByStaffId(50004),subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 26),lrepo.findByStaffId(50004),subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 27),lrepo.findByStaffId(50004),subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 28),lrepo.findByStaffId(50004),subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 29),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
//        schrepo.save(new Schedule(LocalDate.of(2019, 8, 30),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(2000)));
        schrepo.save(new Schedule(LocalDate.of(2020, 8, 31),lrepo.findByStaffId(50002),subrepo.findBySubjectId(1),crepo.findByClassId(1000)));

        // Month of Sept 2019

        // Month of Oct 2019

        // Month of Nov 2019


        
        
        log.info("Testing log in...");
        log.info("End of CmdRunner3"); 
    }
}
