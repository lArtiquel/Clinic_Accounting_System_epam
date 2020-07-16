# Clinic Accounting System


### Clinic Accounting System tech stack:
* Java 8.
* Servlets.
* MySQL.
* C3P0.
* JSP, JSTL, EL.
* HTML, JS, Bootstrap, Datatables, Date picker.
* Log4j.
* Lombok.
* JavaMail.

### Deploy

Check deployed app [here](https://clinic-accounting-system.herokuapp.com/).

Couple accounts with valid creds for you to test the app(please, don't change this creds):

* admin role:
    * login: admin;
    * pass: admin;
	
* doc role:
	* login: firstDoc;
	* pass: firstDoc;
	
* patient/user role:
	* login: firstUser;
	* pass: firstUser;
	
If you are getting page with 500 error code when trying to login, that, probably, means that my hosting time for MySql db expired. 
If so, then write me on _tsvirkoartem@gmail.com_ and i'll fix it.

## Functionality overview:

* Sign In.
* Registration.
* Password recovery by email.
* Administrator
  * Can view/sort/search/add/delete `common` and `only for personal` events.
  * Can view/sort/search/add/delete doctors.
  * Can view/sort/search/edit/delete patients.
  * Can view/sort/search appointments and delete all old non-closed appointments by date.
* Doctor
  * Can view/sort/search/add `common` and `only for personal` events.
  * Can view/edit his own credentials and personal info.
  * Can view/sort/search doctors and make an appointment to other doctor.
  * Can view/sort/search patients and make edits in patient's medical history.
  * Can view/sort/search `him as a doctor` appointments and close them with a note in medical history.
  * Can view/sort/search `him as a patient` appointments.
* Patient
  * Can view/sort/search `common` events.
  * Can view/edit his own credentials and personal info.
  * Can view/sort/search doctors and make appointments to them.
  * Can view/sort/search his own appointments.

## Screenshots

### Sign In
<p align="center">
	<img src="src/test/results/screenshots/common/sign_in.jpg">
</p>


### Registration

#### Username and password
<p align="center">
	<img src="src/test/results/screenshots/common/registration_u_p.jpg">
</p>

#### Personal info application
<p align="center">
	<img src="src/test/results/screenshots/common/registration-pers_info_application.jpg">
</p>

### Password recovery by email
<p align="center">
	<img src="src/test/results/screenshots/common/pass_recovery-enter_username.jpg">
</p>


### Patient

#### Managing account info
<p align="center">
	<img src="src/test/results/screenshots/patient/patient-edit_personal_info.jpg">
	<img src="src/test/results/screenshots/patient/patient-edit_credentials.jpg">
	<img src="src/test/results/screenshots/patient/patient-edit_credentials_submission_result.jpg">
</p>

#### Making appointments
<p align="center">
	<img src="src/test/results/screenshots/patient/patient-make_app_form.jpg">
	<img src="src/test/results/screenshots/patient/patient-make_app_result.jpg">
</p>


### Doctor

#### Home Page
<p align="center">
	<img src="src/test/results/screenshots/doctor/doctor-home_page.jpg">
</p>

#### Creating new events
<p align="center">
	<img src="src/test/results/screenshots/doctor/doctor-add_new_event_form.jpg">
</p>

#### Personal information page

##### As patient
<p align="center">
	<img src="src/test/results/screenshots/doctor/doctor-personal_information_as_a_patient.jpg">
</p>
##### As doc
<p align="center">
	<img src="src/test/results/screenshots/doctor/doctor-personal_information_as_a_doctor.jpg">
</p>
##### Changing personal info
<p align="center">
	<img src="src/test/results/screenshots/doctor/doctor-personal_information_form.jpg">
	<img src="src/test/results/screenshots/doctor/doctor-personal_information_form_submission_result.jpg">
</p>

#### Managing patients
<p align="center">
	<img src="src/test/results/screenshots/doctor/doctor-find_patient_medicalHistory_changing_form.jpg">
	<img src="src/test/results/screenshots/doctor/doctor-find_patient_medicalHistory_changing_form_result.jpg">
</p>

#### Managing appointments
<p align="center">
	<img src="src/test/results/screenshots/doctor/doctor-my_appointments_closing_appointment_form_submission_result.jpg">
</p>


### Admin

#### Home
<p align="center">
	<img src="src/test/results/screenshots/admin/admin-home.jpg">
</p>

#### Managing events
<p align="center">
	<img src="src/test/results/screenshots/admin/admin-create_event_form.jpg">
	<img src="src/test/results/screenshots/admin/admin-delete_event.jpg">
</p>

#### Managing doctors
<p align="center">
	<img src="src/test/results/screenshots/admin/admin-add_doctor_form.jpg">
	<img src="src/test/results/screenshots/admin/admin-add_doctor_form_2.jpg">
	<img src="src/test/results/screenshots/admin/admin-delete_doctor.jpg">
</p>

#### Managing patients
<p align="center">
	<img src="src/test/results/screenshots/admin/admin-edit_patients_info.jpg">
	<img src="src/test/results/screenshots/admin/admin-delete_patient.jpg">
</p>

#### Managing appointments
<p align="center">
	<img src="src/test/results/screenshots/admin/admin-apps.jpg">
	<img src="src/test/results/screenshots/admin/admin-delete_apps_before_date.jpg">
</p>


### Recovery

#### Email code
<p align="center">
	<img src="src/test/results/screenshots/recovery/email_message.png">
</p>

#### Enter code page
<p align="center">
	<img src="src/test/results/screenshots/recovery/enter_code.png">
</p>

#### Pass recovery success
<p align="center">
	<img src="src/test/results/screenshots/recovery/success.png">
</p>

### Mobile look

<p align="center">
  <img src="src/test/results/screenshots/mobile/login.jpg" width="300" height="420">
  <img src="src/test/results/screenshots/mobile/admin-doctors.jpg" width="300" height="420">
  <img src="src/test/results/screenshots/mobile/admin-doctors-addNew.jpg" width="300" height="420">
  <img src="src/test/results/screenshots/mobile/admin-appointments.jpg" width="300" height="420">
  <img src="src/test/results/screenshots/mobile/admin-appointments-delete.jpg" width="300" height="420">
</p>


**Cheers!**

