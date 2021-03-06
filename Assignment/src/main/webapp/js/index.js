const app = angular.module("myApp", ["ngRoute"]);

function NoiTrang($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix("");
    $routeProvider
        .when("/homepage", {
            templateUrl: "TrangTru.html",
            controller: "homepageCtrl"
        })
        .when("/introduce", {
            templateUrl: "GioiThieu.html"
        })
        .when("/contact", {
            templateUrl: "LienHe.html"
        })
        .when("/feedback", {
            templateUrl: "Gopy.html"
        })
        .when("/login", {
            templateUrl: "login.html",
            controller: "loginCtrl"
        })
        .when("/dangki", {
            templateUrl: "dangky.html",
            controller: "dangkyCtrl"
        })
        .when("/Doimatkhau", {
            templateUrl: "Doipass.html",
            controller: "doipassCtrl"
        })
        .when("/Quenmatkhau", {
            templateUrl: "Quenpass.html",
            controller: "quenpassCtrl"
        })
        .when("/CapnhapAcc", {
            templateUrl: "capnhaptk.html",

        })
        .when("/trangThi/:name", {
            templateUrl: "trangthi.html",
            controller: "trangThiCtrl"
        })
        .when("/qlUser", {
            templateUrl: "User.html",
            controller: "qlUserCtrl"
        })
}

function Main($rootScope, $http) {
    $rootScope.students;
    $rootScope.subjects;
    $rootScope.users;
    $rootScope.isLoading = false;

    const url1 = "https://6214c88d89fad53b1f1ed1c8.mockapi.io/Students";
    $rootScope.isLoading = true;
    $http.get(url1)
        .then(function(response) {
            $rootScope.students = response.data;
            $rootScope.isLoading = false;
            window.location.href = "#homepage";
        })
        .catch(function(error) {
            console.log(error);
            $rootScope.isLoading = false;
        })

    const url2 = "https://6214c88d89fad53b1f1ed1c8.mockapi.io/Subjects";
    $rootScope.isLoading = true;
    $http.get(url2)
        .then(function(response) {
            $rootScope.subjects = response.data;
            $rootScope.isLoading = false;
        })
        .catch(function(error) {
            console.log(error);
            $rootScope.isLoading = false;
        })

    const url3 = "https://6214c88d89fad53b1f1ed1c8.mockapi.io/User/0";
    $rootScope.isLoading = true;
    $http.get(url3)
        .then(function(response) {
            $rootScope.users = response.data;
            $rootScope.isLoading = false;
        })
        .catch(function(error) {
            console.log(error);
            $rootScope.isLoading = false;
        })
}

function DangNhap($scope, $rootScope, $http) {
    $scope.student = {};
    $scope.dangNhap = function() {
        var check = true;
        $rootScope.students.forEach(sv => {
            if (sv.username == $scope.student.username) {
                if (sv.password != $scope.student.password) {
                    Swal.fire({
                        icon: 'error',
                        title: '????ng nh???p th???t b???i',
                        text: 'M???t kh???u kh??ng ch??nh x??c !'
                    });
                    check = false;
                } else {
                    const url3 = "https://6214c88d89fad53b1f1ed1c8.mockapi.io/User";
                    $rootScope.isLoading = true;
                    const apiUpdate = url3 + "/0";
                    $http.put(apiUpdate, sv)
                        .then(function(response) {
                            $rootScope.isLoading = false;
                            Main($rootScope, $http);
                            Swal.fire({
                                icon: 'success',
                                title: '????ng nh???p th??nh c??ng',
                                showConfirmButton: false,
                                timer: 2000
                            });
                        })
                        .catch(function(error) {
                            console.log(error);
                            $rootScope.isLoading = false;
                        })
                    check = false;
                    return;
                }
            }
        });
        if (check == true) {
            Swal.fire({
                icon: 'error',
                title: '????ng nh???p th???t b???i',
                text: 'T??i kho???n kh??ng t???n t???i !'
            });
        }
    }
}


function DangKy($scope, $rootScope, $http) {
    $scope.newStudent = {};
    $scope.dangKy = function() {
        const url1 = "https://6214c88d89fad53b1f1ed1c8.mockapi.io/Students";
        $rootScope.isLoading = true;
        $http.post(url1, $scope.newStudent)
            .then(function(response) {
                $rootScope.isLoading = false;
                Swal.fire({
                    icon: 'success',
                    title: '????ng k?? th??nh c??ng',
                    text: 'Chuy???n h?????ng ?????n trang ch??? !',
                    showConfirmButton: false,
                    timer: 2000
                });
                containerUser($rootScope, $http);
            }).catch(function(error) {
                console.log(error);
                $rootScope.isLoading = false;
            })
    }
}

function DangXuat($scope, $rootScope, $http) {
    $scope.dangXuat = function() {
        Swal.fire({
                title: 'B???n mu???n ????ng xu???t t??i kho???n?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#7952b3',
                confirmButtonText: '????ng xu???t!'
            })
            .then((result) => {
                if (result.isConfirmed) {
                    $scope.userNull = {
                        "id": "0",
                        "username": null,
                        "password": null,
                        "fullname": null,
                        "email": null,
                        "gender": null,
                        "birthday": null,
                        "schoolfee": null,
                        "marks": null
                    }
                    const url3 = "https://6214c88d89fad53b1f1ed1c8.mockapi.io/User";
                    $rootScope.isLoading = true;
                    const apiUpdate = url3 + "/0";
                    $http.put(apiUpdate, $scope.userNull)
                        .then(function(response) {
                            $rootScope.isLoading = false;
                            Swal.fire({
                                title: '????ng xu???t th??nh c??ng!',
                                icon: 'success',
                                showConfirmButton: false,
                                timer: 2000
                            });
                            Main($rootScope, $http);
                        })
                        .catch(function(error) {
                            console.log(error);
                            $rootScope.isLoading = false;
                        })
                    return;
                }
            });
    };
}

function QuenPassword($scope, $rootScope) {
    $scope._tenTaiKhoan;
    $scope._email;
    $scope.quenMK = function() {
        var check = true;
        $rootScope.students.forEach(sv => {
            if (sv.username == $scope._tenTaiKhoan && sv.email == $scope._email) {
                $scope.matKhau = sv.password;
                Swal.fire({
                    icon: 'success',
                    title: 'X??c nh???n th??nh c??ng!!',
                    text: 'M???t kh???u c???a b???n l??: ' + $scope.matKhau
                });
                check = false;
                window.location.href = "#homepage";
                return;
            }
        });
        if (check == true) {
            Swal.fire({
                icon: 'error',
                title: 'B???n ???? nh???p sai th??ng tin c???a t??n ????ng nh???p ho???c email!',
                text: 'Vui l??ng nh???p l???i'
            });
        }
    }
}

function DoiPassword($scope, $rootScope, $http) {
    $scope.matKhauHienTai;
    $scope.matKhauMoi;
    $scope.xacNhanMatKhau;
    $scope.doiMK = function() {
        $rootScope.students.forEach(sv => {
            if ($rootScope.users.username == sv.username) {
                if ($scope.matKhauHienTai != sv.password) {
                    Swal.fire({
                        icon: 'error',
                        title: '?????i m???t kh???u th???t b???i!',
                        text: 'M???t kh???u hi???n t???i kh??ng ch??nh x??c !'
                    });
                } else {
                    $rootScope.users.password = $scope.matKhauMoi;
                    for (i = 0; i < $rootScope.students.length; i++) {
                        if ($rootScope.users.username == $rootScope.students[i].username) {
                            const url3 = "https://6214c88d89fad53b1f1ed1c8.mockapi.io/Students";
                            $rootScope.isLoading = true;
                            const apiUpdate = url3 + "/" + i;
                            $http.put(apiUpdate, $rootScope.users)
                                .then(function(response) {
                                    $rootScope.isLoading = false;
                                    Main($rootScope, $http);
                                    Swal.fire({
                                        icon: 'success',
                                        title: '?????i m???t kh???u th??nh c??ng !',
                                        showConfirmButton: false,
                                        timer: 2000
                                    });
                                })
                                .catch(function(error) {
                                    console.log(error);
                                    $rootScope.isLoading = false;
                                })
                            return;
                        }
                    }
                }
            }
        })
    }
}

function CapnhapAcc($scope, $rootScope, $http) {
    $scope.taiKhoan = $rootScope.users;
    $scope.capNhat = function() {
        $rootScope.students.forEach(sv => {
            if (sv.username == $scope.taiKhoan.username) {
                for (i = 0; i < $rootScope.students.length; i++) {
                    if ($scope.taiKhoan.username == $rootScope.students[i].username) {
                        const url3 = "https://6214c88d89fad53b1f1ed1c8.mockapi.io/Students";
                        $rootScope.isLoading = true;
                        const apiUpdate = url3 + "/" + i;
                        $http.put(apiUpdate, $scope.taiKhoan)
                            .then(function(response) {
                                $rootScope.isLoading = false;
                                containerUser($rootScope, $http);
                            })
                            .catch(function(error) {
                                console.log(error);
                                $rootScope.isLoading = false;
                            })
                        return;
                    }

                }
            }
        });
    }
}


function LoadMonHocTrangTru($scope, $rootScope) {
    $scope.monHocHome = $rootScope.subjects;
}

function TrangThi($scope, $timeout, $routeParams, $http, $rootScope) {

    $scope.tenAnh;
    $scope.tenBai;
    $rootScope.subjects.forEach(sb => {
        if (sb.Id == $routeParams.name) {
            $scope.tenAnh = sb.Logo;
            $scope.tenBai = sb.Name;
        }
    });

    $scope.listQuiz;
    $scope.index = 0;
    $scope.check = false;
    $scope.random = 0;
    $scope.mang = [0];
    $scope.diem = 0;
    $scope.timer = 900;
    $scope.stopped;

    const url = "http://127.0.0.1:5500/ASS/Quizs/" + $routeParams.name + ".js";
    $rootScope.isLoading = true;
    $http.get(url)
        .then(function(response) {
            $scope.listQuiz = response.data;
            $scope.countdown = function() {
                $scope.stopped = $timeout(function() {
                    if ($scope.timer == 0) {
                        $timeout.cancel($scope.stopped);
                        Swal.fire({
                            icon: 'warning',
                            title: 'H???t gi??? l??m b??i',
                            text: 'S??? ??i???m m?? b???n ?????t ???????c l??: ' + $scope.diem,
                            showConfirmButton: false,
                            timer: 4000
                        });
                        window.location.href = "#homepage";
                    } else {
                        $scope.timer--;
                        $scope.countdown();
                    }
                }, 1000);
            };
            $scope.countdown();

            for (let i = 0; i < $scope.mang.length; i++) {
                $scope.random = Math.floor(Math.random() * $scope.listQuiz.length);
                if ($scope.random > $scope.listQuiz.length - 1) {
                    continue;
                }
                if ($scope.mang[i] == $scope.random) {
                    continue;
                }
            }
            $scope.mang.push($scope.random);
            $scope.index++;
            $scope.thi = $scope.listQuiz[$scope.random];
            console.log($scope.mang);

            $rootScope.isLoading = false;
        })
        .catch(function(error) {
            console.log(error);
            $rootScope.isLoading = false;
        })

    $scope.traLoi = function() {
        var ans = $('input[name=cauHoi]:checked').val();
        if ($scope.thi.AnswerId == ans) {
            Swal.fire({
                icon: 'success',
                title: '????p ??n ch??nh x??c',
                showConfirmButton: false,
                timer: 2000
            });
            $scope.diem++;
            $scope.check = true;
        } else {
            Swal.fire({
                icon: 'error',
                title: '????p ??n kh??ng ch??nh x??c',
                showConfirmButton: false,
                timer: 2000
            });
            $scope.check = true;
        }
    }
    $scope.next = function() {
        for (let i = 0; i < $scope.mang.length; i++) {
            $scope.random = Math.floor(Math.random() * $scope.listQuiz.length);
            if ($scope.random > $scope.listQuiz.length - 1) {
                continue;
            }
            if ($scope.mang[i] == $scope.random) {
                continue;
            }
        }
        $scope.mang.push($scope.random);
        $scope.index++;
        $scope.thi = $scope.listQuiz[$scope.random];
        console.log($scope.mang);

        $scope.check = false;
    }

    $scope.ketThuc = function() {
        Swal.fire({
            title: 'B???n mu???n k???t th??c b??i ki???m tra?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#7952b3',
            confirmButtonText: 'Ch???c ch???n!'
        }).then((result) => {
            if (result.isConfirmed) {
                $timeout.cancel($scope.stopped);
                Swal.fire({
                    icon: 'success',
                    title: 'K???t th??c!',
                    text: 'S??? ??i???m c???a b???n l??: ' + $scope.diem,
                    showConfirmButton: false,
                    timer: 4000
                });
                location.replace("Assignment.html");
            }
        })
    }
}

function checkQLUser($scope, $http) {
    $scope.index = -1;
    $scope.isLoading = false;
    $scope.isSuccess = false;
    $scope.message = "";
    $scope.students = [];
    $scope.student = {
        id: "",
        username: "",
        password: "",
        fullname: "",
        email: "",
        ngaysinh: "",
        tien: "",
    };

    const url = 'https://6214c88d89fad53b1f1ed1c8.mockapi.io/Students';
    $scope.isLoading = true;
    $http.get(url)
        .then(function(response) {
            $scope.students = response.data;
            $scope.isLoading = false;
        })
        .catch(function(error) {
            console.log(error);
            $scope.isLoading = false;
        });


    $scope.creat = function(event) {
        event.preventDefault();

        $scope.isLoading = true;
        $http.post(url, $scope.student)
            .then(function(response) {
                $scope.isLoading = false;
                $scope.students.push(response.data);
                Swal.fire({
                    title: 'Th??m m???i th??nh c??ng',
                    icon: 'OK',
                })
                $scope.isSuccess = true;
            })
            .catch(function(error) {
                console.log(error);
                $scope.isLoading = false;
                $scope.message = "Th??m m???i th???t b???i";
                $scope.isSuccess = false;
            });
    }

    $scope.Delete = (index) => {
        console.log('xoa');
        const id = $scope.students[index].id;
        const apiDelete = url + "/" + id;

        $http.delete(apiDelete)
            .then(function(response) {
                $scope.students.splice(index, 1);
            })
            .catch(function(error) {
                console.log(error);
            })
    }
    $scope.edit = (index) => {
        console.log($scope.students)
        $scope.index = index;
        $scope.student = angular.copy($scope.students[$scope.index]);
    }

    $scope.update = () => {
        $http.put(url + '/' + $scope.students[$scope.index].id, $scope.student)
            .then(response => {
                $scope.students[$scope.index] = angular.copy($scope.student);
                Swal.fire({
                    title: 'C???p nh???t th??nh c??ng',
                    icon: 'OK',
                })
            })
    }
}




app.config(NoiTrang);
app.controller("MainCtrl", Main);
app.controller("loginCtrl", DangNhap);
app.controller("dangXuatCtrl", DangXuat);
app.controller("dangkyCtrl", DangKy);
app.controller("quenpassCtrl", QuenPassword);
app.controller("doipassCtrl", DoiPassword);
app.controller("capnhaptkCtrl", CapnhapAcc);
app.controller("homepageCtrl", LoadMonHocTrangTru);
app.controller("trangThiCtrl", TrangThi);
app.controller("qlUserCtrl", checkQLUser);