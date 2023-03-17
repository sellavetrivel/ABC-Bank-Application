<%@page import="java.sql.ResultSet"%>
<%@page import="com.bank.database.DbQueries"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.bank.database.DbConnection"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>View customer Account</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <body id="page-top">
        <%
        String admin=session.getAttribute("admin").toString();
        %>

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">ABC <sup>Bank</sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

               


                <!-- Heading -->
                <div class="sidebar-heading">
                    Actions
                </div>




                <hr class="sidebar-divider">


                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item active">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                       aria-expanded="true" aria-controls="collapsePages">
                        <i class="fas fa-fw fa-folder"></i>
                        <span>Customer</span>
                    </a>
                    <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Customer:</h6>
                            <a class="collapse-item" href="AdminCreateAccountForUser.jsp">Create Customer Account</a>
                            <a class="collapse-item" href="AdminViewUserAccount.jsp">View Account</a>

                        </div>
                    </div>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="AdminDepositForUser.jsp">
                        <i class="fas fa-fw fa-bell"></i>
                        <span>Deposit</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AdminWithdrawForUser.jsp">
                        <i class="fas fa-fw fa-pound-sign"></i>
                        <span>Withdraw</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AdminViewUserAccountStatement.jsp">
                        <i class="fas fa-fw fa-file-alt"></i>
                        <span>View Statement</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AdminSearchUsers.jsp">
                        <i class="fas fa-fw fa-search"></i>
                        <span>Search Customers</span></a>
                </li>



                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>



            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <form class="form-inline">
                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>
                        </form>

                        <!-- Topbar Search -->
                        

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">


                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=admin%></span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="index.html">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Customer Details</h1>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Customer Details</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Bank Name</th>
                                                <th>Customer Name</th>
                                                <th>E-mail</th>
                                                <th>Gender</th>
                                                <th>Age</th>
                                                <th>Branch Name</th>
                                                <th>Address</th>
                                                <th>Account Number</th>
                                                <th>IFSC Code</th>
                                                <th>Created Date & Time</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <%
                                                try {
                                                    DbConnection dbb = new DbConnection();
                                                    Connection con = dbb.getConnection();
                                                    PreparedStatement ps = con.prepareStatement(DbQueries.ADMIN_VIEW_USER_ACCOUNT);
                                                    ResultSet rs = ps.executeQuery();
                                                    while (rs.next()) {
                                                        String bankname = rs.getString("bankname");
                                                        String firstname = rs.getString("firstname");
                                                        String lastname = rs.getString("lastname");
                                                        String mail = rs.getString("mail");
                                                        String gender = rs.getString("gender");
                                                        String age = rs.getString("age");
                                                        String branchname = rs.getString("branchname");
                                                        String address = rs.getString("address");
                                                        String accno = rs.getString("accno");
                                                        String ifsc = rs.getString("ifsc");
                                                        String datetime = rs.getString("datetime");

                                            %>
                                            <tr>
                                                <td><%=bankname%></td>
                                                <td><%=firstname%>&nbsp;<%=lastname%></td>
                                                <td><%=mail%></td>
                                                <td><%=gender%></td>
                                                <td><%=age%></td>
                                                <td><%=branchname%></td>
                                                <td><%=address%></td>
                                                <td><%=accno%></td>
                                                <td><%=ifsc%></td>
                                                <td><%=datetime%></td>
                                                <td>
                                                    <a href="AdminEditCustomerDetails.jsp?bankname=<%=bankname%>&firstname=<%=firstname%>&lastname=<%=lastname%>&mail=<%=mail%>&gender=<%=gender%>&age=<%=age%>&branchname=<%=branchname%>&address=<%=address%>&acno=<%=accno%>&ifsc=<%=ifsc%>   "><button class="btn btn-sm btn-primary">Edit</button></a>
                                                    <a href="AdminDeleteCustomerDetails.jsp?accno=<%=accno%>&firstname=<%=firstname%>&lastname=<%=lastname%>"><button class="btn btn-sm btn-danger">Delete</button></a>
                                                </td>
                                            </tr>

                                            <%                              }
                                                } catch (Exception e) {
                                                    System.out.println(e);
                                                }
                                            %>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy;ABC Bank 2020</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
            <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>


    </body>

</html>