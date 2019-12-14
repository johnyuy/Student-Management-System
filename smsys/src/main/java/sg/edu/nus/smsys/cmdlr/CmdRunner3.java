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
public class CmdRunner3 implements CommandLineRunner {
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
		log.info("Creating Schdule...");

		// 19/20-S2 Class 1000
		// Month of August 2019
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 1), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 2), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 3), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 4), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 5), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 6), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 7), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 8), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 9), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 10), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 11), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 12), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 13), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 14), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 15), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 16), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 17), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 18), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 19), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 20), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 21), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 22), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 23), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 24), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 25), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 26), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 27), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 28), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 29), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 30), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 31), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));

		// Month of Sept 2019
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 1), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 2), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 3), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 4), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 5), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 6), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 7), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 8), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 9), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 10), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 11), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 12), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 13), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 14), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 15), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 16), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 17), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 18), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 19), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 20), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 21), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 22), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 23), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 24), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 25), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 26), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 27), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 28), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 29), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 30), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));

		// Month of Oct 2019
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 1), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 2), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 3), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 4), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 5), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 6), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 7), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 8), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 9), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 10), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 11), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 12), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 13), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 14), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 15), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 16), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 17), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 18), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 19), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 20), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 21), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 22), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 23), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 24), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 25), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 26), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 27), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 28), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 29), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 30), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 31), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));

		// Month of Nov 2019
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 1), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 2), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 3), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 4), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 5), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 6), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 7), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 8), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 9), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 10), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 11), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 12), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 13), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 14), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 15), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 16), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 17), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 18), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 19), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 20), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 21), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 22), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 23), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 24), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 25), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 26), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 27), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 28), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 29), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 30), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
	
		// Month of Dec 2019
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 1), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 2), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 3), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 4), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 5), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 6), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 7), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 8), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 9), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 10), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 11), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 12), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 13), lrepo.findByStaffId(50002), subrepo.findBySubjectId(2),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 14), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 15), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 16), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 17), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 18), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 19), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 20), lrepo.findByStaffId(50003), subrepo.findBySubjectId(3),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 21), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 22), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 23), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 24), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 25), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 26), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 27), lrepo.findByStaffId(50003), subrepo.findBySubjectId(4),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 28), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 29), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 30), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 31), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));

		// 19/20-S2 Class 1001
		// Month of August 2019
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 1), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 2), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 3), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 4), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 5), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 6), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 7), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 8), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 9), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 10), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 11), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 12), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 13), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 14), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 15), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 16), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 17), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 18), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 19), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 20), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 21), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 22), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 23), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 24), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 25), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 26), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 27), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 28), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 29), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 8, 30), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 8, 31), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));

		// Month of Sept 2019
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 1), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 2), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 3), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 4), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 5), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 6), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 7), lrepo.findByStaffId(50004), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 8), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 9), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 10), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 11), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 12), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 13), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 14), lrepo.findByStaffId(50004), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 15), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 16), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 17), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 18), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 19), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 20), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 21), lrepo.findByStaffId(50004), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 22), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 23), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 24), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 25), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 26), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 27), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 28), lrepo.findByStaffId(50004), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 9, 29), lrepo.findByStaffId(50002), subrepo.findBySubjectId(1),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 9, 30), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		// Month of Oct 2019
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 1), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 2), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 3), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 4), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 5), lrepo.findByStaffId(50004), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 6), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 7), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 8), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 9), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 10), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 11), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 12), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 13), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 14), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 15), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 16), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 17), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 18), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 19), lrepo.findByStaffId(50003), subrepo.findBySubjectId(6),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 20), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 21), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 22), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 23), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 24), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 25), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 26), lrepo.findByStaffId(50003), subrepo.findBySubjectId(7),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 10, 27), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 28), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 29), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 30), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 10, 31), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		// Month of Nov 201
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 1), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 2), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 3), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 4), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 5), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 6), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 7), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 8), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 9), lrepo.findByStaffId(50004), subrepo.findBySubjectId(5),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 10), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 11), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 12), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 13), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 14), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 15), lrepo.findByStaffId(50004), subrepo.findBySubjectId(11),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 16), lrepo.findByStaffId(50004), subrepo.findBySubjectId(5),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 17), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 18), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 19), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 20), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 21), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 22), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 23), lrepo.findByStaffId(50004), subrepo.findBySubjectId(5),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 24), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 25), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 26), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 27), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 28), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 11, 29), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 11, 30), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1000)));
		// Month of Dec 2019
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 1), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 2), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 3), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 4), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 5), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 6), lrepo.findByStaffId(50004), subrepo.findBySubjectId(6),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 7), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 8), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 9), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 10), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 11), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 12), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 13), lrepo.findByStaffId(50004), subrepo.findBySubjectId(7),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 14), lrepo.findByStaffId(50004), subrepo.findBySubjectId(5),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 15), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 16), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 17), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 18), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 19), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 20), lrepo.findByStaffId(50004), subrepo.findBySubjectId(8),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 21), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 22), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 23), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 24), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 25), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 26), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 27), lrepo.findByStaffId(50004), subrepo.findBySubjectId(9),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 28), lrepo.findByStaffId(50004), subrepo.findBySubjectId(5),crepo.findByClassId(1001)));
//		schrepo.save(new Schedule(LocalDate.of(2019, 12, 29), lrepo.findByStaffId(50003), subrepo.findBySubjectId(5),crepo.findByClassId(1000)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 30), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		schrepo.save(new Schedule(LocalDate.of(2019, 12, 31), lrepo.findByStaffId(50004), subrepo.findBySubjectId(10),crepo.findByClassId(1001)));
		log.info("Testing log in...");
		log.info("End of CmdRunner3");
	}
}
