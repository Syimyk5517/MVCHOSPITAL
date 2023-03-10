package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Appointment;
import peaksoft.models.Hospital;
import peaksoft.services.*;

@Controller
@RequestMapping("/{id}/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @GetMapping
    String getAllAppointments(@PathVariable("id") Long id, Model model){
        model.addAttribute("appointments",appointmentService.findAll(id));
        model.addAttribute("hospitalId",id);
        return "appointment/appointments";
    }
    @GetMapping("saveAppointment")
    String save(Model model,@PathVariable("id")Long id){
        model.addAttribute("appointment",new Appointment());
        model.addAttribute("patients",patientService.getAllPatient(id));
        model.addAttribute("doctors",doctorService.getAll(id));
        model.addAttribute("departments",departmentService.getAll(id));
        model.addAttribute("hospitalId",id);
        return "appointment/saveAppointment";
    }
    @PostMapping("/new")
    String create(@ModelAttribute("appointment") Appointment appointment,@RequestParam("patientId")Long patientId,@RequestParam("doctorId")Long doctorId,
                  @RequestParam("departmentId") Long departmentId,@PathVariable("id") Long hospitalId ){
        System.out.println("sasdd");
        appointmentService.save(hospitalId,patientId,doctorId,departmentId,appointment);
        return "redirect:/{id}/appointments";
    }
}
