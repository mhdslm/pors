var pors = angular.module('Pors', [ 'ui.router' ]);

pors.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/frontpage');

	$stateProvider
	// ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
	.state('register', {
		url : '/register',
		templateUrl : 'project/app/templates/register.html',
		controller : 'RegisterCtrl as register'
	}).state('login', {
		url : '/login',
		templateUrl : 'project/app/templates/login.html',
		controller : 'LoginCtrl as login'
	}).state('home', {
		url : '/home',
		templateUrl : 'project/app/templates/home.html',
		controller : 'HomeCtrl as home'
	}).state('home.patient', {
		url : '/patient',
		templateUrl : 'project/app/templates/homepatient.html',
	}).state('home.doctor', {
		url : '/doctor',
		templateUrl : 'project/app/templates/homedoctor.html',
	}).state('patientInfo', {
		url : '/patientInfo',
		templateUrl : 'project/app/templates/patientinfo.html',
		controller : 'PatientInfoCtrl as patientInfo'

	// ============== TESTING APPOINTMENT REQUEST AS SUB
	// STATE===================
	})/*
		 * .state('appointmentRequest', { url : 'appointmentRequest',
		 * templateUrl : 'project/app/templates/appointment.html', controller :
		 * 'AppointmentCtrl as appointmentRequest'
		 */
	// to list doctors
	.state('doctorRegister', {
		url : '/doctorRegister',
		templateUrl : 'project/app/templates/doctorregister.html',
		controller : 'DoctorRegisterCtrl as doctorRegister'
	// store doctor info
	}).state('doctorInfo', {
		url : '/doctorInfo',
		templateUrl : 'project/app/templates/doctorinfo.html',
		controller : 'DoctorInfoCtrl as doctorInfo'
	// list appointments
	}).state('appointmentHistory', {
		url : '/appointmentHistory',
		templateUrl : 'project/app/templates/appointmenthistory.html',
		controller : 'AppointmentHistoryCtrl as appointmentHistory'
	}).state('home.doctor.appointment', {
		url : '/detail/:appId',
		templateUrl : 'project/app/templates/docAppDetail.html',
		controller : 'AppDetailCtrl as appDetail'
	}).state('showDocApps', {
		url : '/showDocApps',
		templateUrl : 'project/app/templates/alldocapps.html',
		controller : 'ShowDocAppsCtrl as showDocApps'
	}).state('myDoctors', {
		url : '/myDoctors',
		templateUrl : 'project/app/templates/mydoctors.html',
		controller : 'MyDoctorsCtrl as myDoctors'
	}).state('myDoctors.appointmentRequest', {
		url : '/:doctorId',
		templateUrl : 'project/app/templates/appointment.html',
		controller : 'AppointmentCtrl as appointmentRequest'

	}).state('frontpage', {
		url : '/frontpage',
		templateUrl : 'project/app/templates/frontpage.html',
	});

});

pors.controller('NavBarCtrl', function(UserService, PatientService,
		DoctorService, $state) {

	var nav = this;

	nav.isActive = function(viewLocation) {

		nav.curUser = UserService.getUser();
		nav.curState = $state.current.url;

		return viewLocation === $state.current.url;
	};

	nav.reset = "";
	nav.logout = function() {
		// ====== user data ==========
		nav.message = "";
		nav.user = {
			id : 0,
			username : "",
			password : "",
			authenticated : false,
			roleId : 0,
			clientId : 0
		};
		UserService.setUser(nav.user);
		UserService.setMessage(nav.message);
		// ===== patient data ==============
		nav.patient = UserService.getUser();
		nav.patient.firstname = "";
		nav.patient.lastname = "";
		nav.patient.street = "";
		nav.patient.city = "";
		nav.patient.state = "";
		nav.patient.zipcode = "";
		nav.patient.phonenumber = "";
		PatientService.setPatient(nav.patient);
		PatientService.setMessage(nav.message);
		// ======= doctor data =================
		nav.doctor = UserService.getUser();

		nav.doctor.doctorId = '';
		nav.doctor.firstName = '';
		nav.doctor.lastName = '';
		nav.doctor.email = '';
		nav.doctor.registered = '';
		console.log('The logged out user info: ');
		console.log(UserService.getUser());
		// PatientService.setPatient(nav.reset);
		DoctorService.setDoctor(nav.doctor);
		DoctorService.setMessage(nav.message);
	};

});

pors
		.service(
				'UserService',
				function($http, $q) {
					var userService = this;
					userService.message = "";

					userService.user = {
						id : 0,
						username : "",
						password : "",
						authenticated : false,
						roleId : 0,
						clientId : 0
					};

					userService.isAuthenticated = function() {
						return userService.user.authenticated;
					}

					userService.setAuthenticated = function(autheticated) {
						return userService.user.authenticated = autheticated;
					}

					userService.getMessage = function() {
						return userService.message;
					}

					userService.setMessage = function(message) {
						userService.message = message;
					}

					userService.getUser = function() {
						return userService.user;
					}

					userService.setUser = function(user) {
						userService.user = user;
					}

					userService.getRole = function() {
						return userService.roleDto;
					}

					userService.setRole = function(roleDto) {
						userService.roleSto = roleDto;
					}

					userService.getClientId = function() {
						return userService.user.clientId;
					}

					userService.setClientId = function(clientId) {
						userService.user.clientId = clientId;
					}

					userService.registerUser = function(user) {

						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/user/register',
										user).then(function(response) {
									console.log('Regi Success');
									return response;
								}, function(response) {
									console.log('Regi Failure');
									return $q.reject(response);
								});
						return promise;
					}

					userService.authenticateUser = function(user) {
						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/user/authenticate',
										user).then(function(response) {
									console.log('Login Success');
									return response;
								}, function(response) {
									console.log('Login Failure');
									return $q.reject(response);
								});
						return promise;
					}
				});

pors
		.service(
				'PatientService',
				function($http, $q, UserService) {

					var patientService = this;
					patientService.message = "";

					patientService.patient = UserService.getUser();
					// patientService.patient = {
					// firstname : "",
					// lastname : "",
					// street : "",
					// city : "",
					// state : "",
					// zipcode : "",
					// phonenumber : ""
					// };

					patientService.patient.firstname = "";
					patientService.patient.lastname = "";
					patientService.patient.street = "";
					patientService.patient.city = "";
					patientService.patient.state = "";
					patientService.patient.zipcode = "";
					patientService.patient.phonenumber = "";

					patientService.getMessage = function() {
						return patientService.message;
					}

					patientService.setMessage = function(message) {
						patientService.message = message;
					}

					patientService.getPatient = function() {
						return patientService.patient;
					}

					patientService.setPatient = function(patient) {
						patientService.patient = patient;
					}

					patientService.getInfo = function(patient) {

						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/patient/submitInfo',
										patient).then(function(response) {
									console.log('Info Saved');
									return response;
								}, function(response) {
									console.log(patient.user)
									console.log('Info Not Saved');
									return $q.reject(response);
								});
						return promise;
					}

					patientService.showDoctors = function(patient) {

						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/patient/listDoctors',
										patient).then(function(response) {
									console.log('List Received Successfully');
									console.log(response);
									return response;
								}, function(response) {
									console.log('Process Failed');
									return $q.reject(response);
								});
						return promise;
					}

					patientService.showUnregisteredDoctors = function(patient) {

						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/patient/listUnregisteredDoctors',
										patient).then(function(response) {
									console.log('List Received Successfully');
									console.log(response);
									return response;
								}, function(response) {
									console.log('Process Failed');
									return $q.reject(response);
								});
						return promise;
					}

				});

pors
		.service(
				'AppointmentService',
				function($http, $q, UserService) {

					var appointmentService = this;
					appointmentService.message = null;

					// appointmentService.appointment = UserService.getUser();

					appointmentService.appointment = {
						appId : 0,
						appDate : "",
						reason : "",
						status : "",
						doctorId : 0,
						id : 0
					}
					// PatientService.getPatient();

					// appointmentService.appointment.appDate = "";
					// appointmentService.appointment.reason = "";

					appointmentService.listOfAppointmens = {
						appointment : {
							appId : "",
							appDate : "",
							reason : ""
						}
					};

					appointmentService.getListOfAppointments = function() {
						return appointmentService.listOfAppointments;
					}

					appointmentService.setListOfAppointments = function(
							listOfAppointments) {
						appointmentService.listOfAppointments = listOfAppointments;
					}

					// appointmentService.appointment.status = "";

					appointmentService.getStatus = function() {
						return appointmentService.appointment.status;
					}

					appointmentService.setStatus = function(status) {
						appointmentService.appointment.status = status;
					}

					appointmentService.getMessage = function() {
						return appointmentService.message;
					}

					appointmentService.setMessage = function(message) {
						appointmentService.message = message;
					}

					appointmentService.getAppointment = function() {
						return appointmentService.appointment;
					}

					appointmentService.setAppointment = function(appointment) {
						appointmentService.appointment = appointment;
					}

					appointmentService.getReason = function() {
						return appointmentService.reason;
					}

					appointmentService.setReason = function(reason) {
						appointmentService.reason = reason;
					}

					// FIX
					appointmentService.makeAppointment = function(patient) {

						var promise = $http
								.post(
										// changethis
										'http://localhost:8088/PatientReportingSys/appointment/makeAppointment',
										patient).then(function(response) {
									console.log('Info Saved');
									return response;
								}, function(response) {
									console.log(patient.user)
									console.log('Info Not Saved');
									return $q.reject(response);
								});
						return promise;
					}

					appointmentService.unregisterDoctor = function(patient) {

						var promise = $http
								.post(
										// changethis
										'http://localhost:8088/PatientReportingSys/appointment/unregisterDoctor',
										patient).then(function(response) {
									console.log('Unregistered');
									return response;
								}, function(response) {
									console.log(patient.user)
									console.log('Failed');
									return $q.reject(response);
								});
						return promise;
					}

					appointmentService.viewAppointments = function(patient) {

						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/appointment/viewAppointments',
										patient).then(function(response) {
									console.log('List Retrieved');
									return response;
								}, function(response) {
									// console.log(patient.user)
									console.log('No list');
								});
						return promise;
					}

					appointmentService.actionOnAppointment = function(
							appointment) {

						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/appointment/action',
										appointment)
								.then(
										function(response) {
											console
													.log('appointment status updated!');
											return response;
										},
										function(response) {
											console
													.log('Failed to update status of appointment');
											return $q.reject(response);
										});
						return promise;
					}

				});

pors.controller("PatientInfoCtrl",
		function(PatientService, UserService, $state) {

			var patientInfo = this;
			patientInfo.patient = UserService.getUser();

			// console.log("Patient Username")
			// console.log("Username is" + patientInfo.patient.username);
			patientInfo.patient.firstName = "";
			patientInfo.patient.lastName = "";
			patientInfo.patient.street = "";
			patientInfo.patient.city = "";
			patientInfo.patient.state = "";
			patientInfo.patient.zipcode = "";
			patientInfo.patient.phoneNumber = "";
			patientInfo.patient.email = "";

			console.log('patient info: ');
			console.log(patientInfo.patient);
			patientInfo.message = PatientService.getMessage();

			patientInfo.collectInfo = function(isValid) {
				if (isValid) {
					var promise = PatientService.getInfo(patientInfo.patient);

					promise.then(function(data) {
						console.log('success');
						console.log(data);
						console.log(patientInfo.patient);
						PatientService.setMessage('Information Saved');
						// changed

						$state.go('login');

					}, function(error) {
						console.log('failure');
						console.log(error);
						// console.log(register.user);
						// register.message = 'Error';
						$state.go('patientInfo');
					});
				}

			};

		});

pors.controller("AppointmentHistoryCtrl", function(UserService, $scope,
		DoctorService, $state, PatientService, AppointmentService) {
	var appointmentHistory = this;
	// appointmentHistory.appointment = AppointmentService.getAppointment();
	appointmentHistory.message = AppointmentService.getMessage();
	// appointmentHistory.patient = PatientService.getPatient();
	appointmentHistory.patient = UserService.getUser();
	appointmentHistory.patient.firstName = "";
	appointmentHistory.patient.lastName = "";
	appointmentHistory.patient.street = "";
	appointmentHistory.patient.city = "";
	appointmentHistory.patient.state = "";
	appointmentHistory.patient.zipcode = "";
	appointmentHistory.patient.phoneNumber = "";
	appointmentHistory.patient.email = "";

	appointmentHistory.displayAppointments = function() {
		var promise = AppointmentService
				.viewAppointments(appointmentHistory.patient);

		promise.then(function(response) {
			console.log('List Received Successfully');
			console.log('Patient info');
			console.log(appointmentHistory.patient);
			// console.log(response.data);
			AppointmentService.setListOfAppointments(response.data);
			appointmentHistory.listOfAppointments = AppointmentService
					.getListOfAppointments();
			console.log(AppointmentService.getListOfAppointments());

			// ========== get status ==========
			// $scope.appointmentStatus = ["accepted", "rejected", "pending"];
			console.log("list of appointments");

			PatientService.setMessage('List Received')
			$state.go('appointmentHistory')
			// return response;
		}, function(error) {
			console.log('Process Failed');
			$state.go('appointmentHistory')
		});

	};

	appointmentHistory.displayAppointments();

});

pors.controller("DoctorRegisterCtrl", function(UserService, DoctorService,
		$state, PatientService, $scope) {

	var doctorRegister = this;
	doctorRegister.message = "";
	$scope.doctorMessage = null;

	doctorRegister.patient = UserService.getUser();
	doctorRegister.patient.firstName = "";
	doctorRegister.patient.lastName = "";
	doctorRegister.patient.street = "";
	doctorRegister.patient.city = "";
	doctorRegister.patient.state = "";
	doctorRegister.patient.zipcode = "";
	doctorRegister.patient.phoneNumber = "";
	doctorRegister.patient.email = "";

	// doctorRegister.patient = PatientService.getPatient();
	console.log('Patient in doctor register:');
	console.log(doctorRegister.patient)
	doctorRegister.user = UserService.getUser();
	console.log('User in doctor register:');
	console.log(doctorRegister.user)
	doctorRegister.doctor = DoctorService.getDoctor();

	console.log('Doctor in doctor register:');
	console.log(doctorRegister.doctor)

	// ========== assign doctor ID separately ==========
	doctorRegister.doctor.id = doctorRegister.user.id;
	console.log('Doctor in doctor register 2:');
	console.log(doctorRegister.doctor);
	// ================================================

	doctorRegister.registerDoctor = function() {
		// doctorRegister.registered = DoctorService.getRegistered();
		console.log('doctorRegister.doctor');
		console.log(doctorRegister.doctor);
		var promise = DoctorService.registerDoctor(doctorRegister.doctor);

		promise.then(function(response) {
			console.log('DOCTOR REGISTER TO PATIENT');
			doctorRegister.message = 'Registration to the doctor successful';
			doctorMessage = 'Registration to the doctor successful';
			alert('Registration to the doctor successful!');
			$state.go('doctorRegister', {}, {
				reload : true
			});
			// $state.go('home.patient');
		}, function(error) {
			console.log('PROCESS FAILED');
			$state.go('home.patient');
		});

	};
	// ======== check if list of doctors is empty in order to disable register
	// to doctor button

	doctorRegister.isEmpty = null;

	doctorRegister.displayDoctors = function() {

		var promise = PatientService
				.showUnregisteredDoctors(doctorRegister.patient);

		promise.then(function(response) {
			console.log('List Received Successfully');
			// console.log(response.data);
			DoctorService.setListOfDoctors(response.data);
			doctorRegister.listOfDoctors = DoctorService.getListOfDoctors();

			console.log("list of doctors");
			console.log(doctorRegister.listOfDoctors);
			if (doctorRegister.listOfDoctors == '') {
				console.log('The doctor list is empty');
				// $scope.isEmpty = true;
				doctorRegister.isEmpty = true;
			} else {
				console.log('The doctor list is not empty');
				// $scope.isEmpty = false;
				doctorRegister.isEmpty = false;
			}
			PatientService.setMessage('List Received')
			$state.go('doctorRegister')
			// return response;
		}, function(error) {
			console.log('Process Failed');
			$state.go('doctorRegister')
		});

	};

	doctorRegister.displayDoctors();
	// doctorRegister.registerDoctor();

});

pors
		.controller(
				"AppointmentCtrl",
				function(UserService, $scope, AppointmentService, $state,
						$stateParams, DoctorService) {

					var appointmentRequest = this;

					appointmentRequest.message = null;
					
					appointmentRequest.appointment = AppointmentService
							.getAppointment();
					appointmentRequest.appointment.appDate = new Date();
					appointmentRequest.appointment.doctorId = $stateParams.doctorId
					appointmentRequest.appointment.user = UserService.getUser();
					appointmentRequest.appointment.username = appointmentRequest.appointment.user.username;

					// ===== current doctorId =============
					$scope.doctorId = $stateParams.doctorId;
					// =====================================

					// =========== get list of all doctors ========
					appointmentRequest.doctorsList = DoctorService
							.getListOfDoctors();
					// $scope.doctorsList = appointmentRequest.doctorsList;

					console.log(appointmentRequest.appointment);

					// ==================== make appointment
					// ===============================

					appointmentRequest.makeAppointment = function(isValid) {
						if (isValid) {
							var promise = AppointmentService
									.makeAppointment(appointmentRequest.appointment);
							console.log(appointmentRequest.appointment);

							promise
									.then(
											function(data) {
												console.log('success');
												console.log(data);
												console
														.log(appointmentRequest.appointment);
												appointmentRequest.message = 'Appointment is succefully scheduled!';
												AppointmentService.setMessage(appointmentRequest.message);
												AppointmentService
														.setReason(appointmentRequest.appointment.reason);
												console.log('The message: ');
												console.log(appointmentRequest.message);
												setTimeout(function(){
												    console.log('inside time out');
												}, 5000);
												//alert('Appointment is succefully scheduled!');
												
												$state.go('myDoctors.appointmentRequest');

											}, function(error) {
												console.log('failure');
												console.log(error);
												$state.go('home.patient');
											});
						}

					};

					appointmentRequest.unregisterDoctor = function() {

						var promise = AppointmentService
								.unregisterDoctor(appointmentRequest.appointment);
						console.log(appointmentRequest.appointment);

						promise
								.then(
										function(data) {
											console.log('success');
											console.log(data);
											console
													.log(appointmentRequest.appointment);
											//AppointmentService
											//		.setMessage('Doctor Unregistered');
											appointmentRequest.message = 'Unregister from doctor successful!';
											appointmentRequest.appointment.reason = "";
											AppointmentService
													.setReason(appointmentRequest.appointment.reason);
											//alert('Unregister from doctor successful!');
											$state.go('home.patient');

										}, function(error) {
											console.log('failure');
											console.log(error);
											$state.go('home.patient');
										});

					};

				});

pors.controller('RoleCtrl', function($scope, $http) {

	$scope.roles = [];

	$http.get('http://localhost:8088/PatientReportingSys/user/roles').success(
			function(data, status, headers, config) {
				console.log(data);
				$scope.roles = data;
			});

});

pors
		.service(
				'DoctorService',
				function($http, $q, UserService) {
					var doctorService = this;
					doctorService.message = "";

					doctorService.doctor = UserService.getUser();

					doctorService.doctor.doctorId = '';
					doctorService.doctor.firstName = '';
					doctorService.doctor.lastName = '';
					doctorService.doctor.email = '';
					doctorService.doctor.registered = '';

					doctorService.listOfDoctors = {
						doctor : {
							id : "",
							firstName : "",
							lastName : "",
							email : ""
						}
					};

					doctorService.getListOfDoctors = function() {
						return doctorService.listOfDoctors;
					}

					doctorService.setListOfDoctors = function(listOfDoctors) {
						doctorService.listOfDoctors = listOfDoctors;
						doctorService.appointments = [];
						doctorService.appointment = [];

					}

					doctorService.getMessage = function() {
						return doctorService.message;
					}

					doctorService.setMessage = function(message) {
						doctorService.message = message;
					}

					doctorService.getDoctor = function() {
						return doctorService.doctor;
					}

					doctorService.setDoctor = function(doctor) {
						doctorService.doctor = doctor;
					}

					doctorService.getRegistered = function() {
						return doctorService.doctor.registered;
					}

					doctorService.setRegistered = function(doctor) {
						doctorService.doctor.registered = doctor;
					}

					doctorService.getAppointment = function() {
						return doctorService.appointment;
					}

					doctorService.setAppointment = function(appointment) {
						doctorService.appointment = appointment;
					}

					doctorService.getAppointments = function() {
						return doctorService.appointments;
					}

					doctorService.setAppointments = function(appointments) {
						doctorService.appointments = appointments;
					}

					doctorService.getInfo = function(doctor) {

						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/doctor/submitInfo',
										doctor).then(function(response) {
									console.log('Info Received Successfully');
									return response;
								}, function(response) {
									console.log('Process Failed');
									return $q.reject(response);
								});
						return promise;
					}

					doctorService.registerDoctor = function(doctor) {

						// CHANGE TO REGISTER DOCTOR
						var promise = $http
								.post(
										'http://localhost:8088/PatientReportingSys/patient/registerDoctor',
										doctor).then(function(response) {
									console.log('Registered Successfully');
									return response;
								}, function(response) {
									console.log('Process Failed');
									return $q.reject(response);
								});
						return promise;

						// console.log("doctorservice");

					}

				});

pors.controller('DoctorInfoCtrl', function(DoctorService, UserService, $state,
		$scope) {
	var doctorInfo = this;

	console.log('In DoctorInfoCtrl');
	doctorInfo.user = UserService.getUser();

	doctorInfo.doctor = UserService.getUser();
	doctorInfo.doctor.doctorId = '';
	doctorInfo.doctor.firstName = '';
	doctorInfo.doctor.lastName = '';
	doctorInfo.doctor.email = '';

	console.log('Doctor info in DoctorInfoCtrl: ');
	console.log(doctorInfo.doctor);

	doctorInfo.message = DoctorService.getMessage();
	doctorInfo.collectInfo = function(isValid) {
		if (isValid) {
			var promise = DoctorService.getInfo(doctorInfo.doctor);
			console.log("Doctor's email is: ");
			console.log(doctorInfo.doctor.email);
			promise.then(function(data) {
				console.log('success');
				console.log(data);
				// DoctorService.setDoctor(doctorInfo.doctor);
				// doctorInfo.doctor = DoctorService.resetDoctorInfo();
				// DoctorService.setDoctor(doctorInfo.doctor);
				$state.go('login', {}, {
					reload : true
				});
			}, function(error) {
				console.log('failure');
				console.log(error);
				console.log(doctorInfo.doctor);
				doctorInfo.message = 'The email already exsits';
				$state.go('doctorInfo');
			});
		}
	}

});

pors.controller('RegisterCtrl', function(UserService, $state) {
	var register = this;

	register.user = {
		username : '',
		password : '',
		roleId : 0
	}
	register.message = UserService.getMessage();

	register.doRegister = function(isValid) {
		if (isValid) {
			var promise = UserService.registerUser(register.user);

			promise.then(function(data) {
				console.log('success');
				console.log(data);
				console.log(register.user);
				UserService.setMessage('Successful Registration');
				UserService.setUser(register.user);
				console.log('User info: ');
				console.log(UserService.getUser());
				// console.log(UserService.getMessage);
				// changed
				if (register.user.roleId == 1) {
					// $state.go('patientInfo');
					$state.go('patientInfo', {}, {
						reload : true
					});
				} else if (register.user.roleId == 2) {
					// $state.go('doctorInfo');
					$state.go('doctorInfo', {}, {
						reload : true
					});
				} else {
					// $state.go('login');
					$state.go('login', {}, {
						reload : true
					});

				}
			}, function(error) {
				console.log('failure');
				console.log(error);
				console.log(register.user);
				register.message = 'Username is taken';
				$state.go('register');
			});
		}

	};
});

pors.controller('AppDetailCtrl', function(AppointmentService, $state,
		$stateParams, $scope, $rootScope) {

	var appDetail = this;
	appDetail.message = null;
	//========= scope message =============
	$rootScope.docAppMessage = null;
	
	//================================
	
	// $scope.appointment = {};
	appDetail.appointments = AppointmentService.getListOfAppointments();
	// appDetail.curAppId = $stateParams.appId;
	// appDetail.currAppointment = AppointmentService.getAppointment();
	// console.log('Current appointment:');
	// console.log(appDetail.currAppointment);
	console.log('All appointments: ');
	console.log(appDetail.appointments);
	appDetail.appointments.status = AppointmentService.getStatus();
	console.log('Testing getting status: ')
	appDetail.curAppId = $stateParams.appId;
	console.log(appDetail.appointments[0].status)
	$scope.appointments = appDetail.appointments;
	$scope.appId = $stateParams.appId;
	// console.log('Current app Id: ');
	// console.log(appDetail.curAppId)

	// ================== Action on Appointment ================================
	appDetail.params = {
		appId : $stateParams.appId,
		status : ''
	}
	appDetail.appointment = AppointmentService.getAppointment();
	appDetail.actionOnAppointment = function(isValid) {
		console.log("In actionOnAppointment");
		// appDetail.appStatus = AppointmentService.getStatus();

		if (isValid) {
			console.log('app params: ');
			console.log(appDetail.params);
			var promise = AppointmentService
					.actionOnAppointment(appDetail.params);

			promise.then(function(data) {
				console.log('app params: ');
				console.log(appDetail.params);
				console.log(data);
				// AppointmentService.setAppointment(appDetail.params);
				$rootScope.docAppMessage = 'Action submitted. An email notification will be sent to the patient.';
				//alert('Action submitted. An email notification will be sent to the patient.');
				$state.go('home.doctor', {}, {
					reload : true
				});
			}, function(error) {
				console.log('failure');
				console.log(appDetail.params);
				console.log(error);
				$state.go('home.doctor');
			});

		}
	}

});

// ============== DESTIN ADDED THURSDAY NIGHT ===================

pors.controller('MyDoctorsCtrl', function(UserService, DoctorService, $state,
		PatientService, $scope, AppointmentService) {

	var myDoctors = this;
	myDoctors.message = AppointmentService.getMessage();
	// myDoctors.patient = PatientService.getPatient();
	myDoctors.patient = UserService.getUser();
	myDoctors.patient.firstName = "";
	myDoctors.patient.lastName = "";
	myDoctors.patient.street = "";
	myDoctors.patient.city = "";
	myDoctors.patient.state = "";
	myDoctors.patient.zipcode = "";
	myDoctors.patient.phoneNumber = "";
	myDoctors.patient.email = "";

	myDoctors.user = UserService.getUser();
	myDoctors.doctor = DoctorService.getDoctor();

	myDoctors.displayDoctors = function() {

		var promise = PatientService.showDoctors(myDoctors.patient);

		promise.then(function(response) {
			console.log('List Received Successfully');
			// console.log(response.data);
			DoctorService.setListOfDoctors(response.data);
			myDoctors.listOfDoctors = DoctorService.getListOfDoctors();
			console.log(DoctorService.getListOfDoctors());
			console.log("list of doctors");

			// ========= to show name of doctors ======
			$scope.doctorsList = myDoctors.listOfDoctors;

			PatientService.setMessage('List Received')
			$state.go('myDoctors')
			// return response;
		}, function(error) {
			console.log('Process Failed');
			$state.go('myDoctors')
		});

	};

	myDoctors.displayDoctors();

});

// ============== DESTIN ADDED THURSDAY NIGHT ===================

pors
		.controller(
				"HomeCtrl",
				function(UserService, AppointmentService, DoctorService,
						$scope, $state, $http, $stateParams, $rootScope) {
					console.log('current state: ')
					console.log($state.current.name);
					$scope.user = UserService.getUser();
					// var home = this;
					var home = this;
					// home.patient = PatientService.getPatient();
					// console.log($state.current)
					if ($state.current.name == 'home.patient') {

						$scope.appointmentRequest = function() {
							UserService.setUser($scope.user);
							$state.go('appointmentRequest');
						};

						$scope.doctorRegister = function() {
							UserService.setUser($scope.user);
							$state.go('doctorRegister');
						};

						
						$scope.appointmentToDoctor = function() {
							UserService.setUser($scope.user);
							$state.go('myDoctors');
						};

						

						$scope.appointmentHistory = function() {
							UserService.setUser($scope.user);
							$state.go('appointmentHistory');
						};

					}

					if ($state.current.name == 'home.doctor') {
						console.log('Message after action submission:');
						console.log($rootScope.docAppMessage);
						// $scope.user = UserService.getUser();
						$scope.showDocAppointments = function() {
							UserService.setUser($scope.user);
							$state.go('showDocApps');
						};
						// ============== Show requested appointments on home
						// page ===================
						home.isEmpty = null;
						home.appointments = [];
						$http(
								{
									url : "http://localhost:8088/PatientReportingSys/doctor/pendingAppointments",
									method : "GET",
									params : {
										doctorId : UserService.getClientId()
									}
								})
								.success(
										function(data, headers, config, status) {
											console.log('appointments: ');
											console.log(data);
											home.appointments = data;
											console
													.log('Appointments in doctor home: ');
											console.log(home.appointments);
											if (home.appointments == '') {
												console.log('no appointments');
												home.isEmpty = true;
											} else {
												console
														.log('There are appointments');
												home.isEmpty = false;
											}
											AppointmentService
													.setListOfAppointments(home.appointments);
										});
					}
				});

pors
		.controller(
				'ShowDocAppsCtrl',
				function(AppointmentService, UserService, DoctorService,
						$state, $http) {
					var showDocApps = this;
					showDocApps.doctor = DoctorService.getDoctor();
					showDocApps.appointments = [];
					$http(
							{
								url : "http://localhost:8088/PatientReportingSys/doctor/appointmentsHistory",
								method : "GET",
								params : {
									doctorId : UserService.getClientId()
								}
							})
							.success(
									function(data, headers, config, status) {
										console.log('appointments: ');
										console.log(data);
										showDocApps.appointments = data;
										console.log('history of appointments');
										console.log(showDocApps.appointments);
										AppointmentService
												.setListOfAppointments(showDocApps.appointments);
									});

				});

// home.appointments.curDoctorId = UserService.getClientId();
// ============================================================================

// More state switch functions to be added

// });

pors.controller('LoginCtrl', function(UserService, $state) {
	var login = this;
	login.user = UserService.getUser();
	login.message = UserService.getMessage();

	login.doLogin = function(isValid) {
		if (isValid) {

			var promise = UserService.authenticateUser(login.user);

			promise.then(function(response) {
				console.log('success');
				console.log(response);
				console.log(response.data);
				console.log(response.data.username);
				UserService.setUser(response.data);
				UserService.setAuthenticated(true);
				UserService.setClientId(response.data.clientId);

				if (login.user.roleId == 1) {
					$state.go('home.patient');// change to patient home
				} else if (login.user.roleId == 2) {
					$state.go('home.doctor');// change to doctor home
				} else {
					$state.go('login');
				}
			}, function(error) {
				console.log('failure');
				login.message = 'Bad credentials';
				$state.go('login');
			});

		}
	};
});