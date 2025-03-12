import { Injectable } from '@angular/core';
import { Employee } from './employee.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  selectedEmployee: Employee = {
    eid: 0,
    name: '',
    age: 0,
    designation: '',
    salary: 0,
  };
  employees: Employee[] = [];
  constructor(private http: HttpClient) {}

  postEmployee(employee: Employee) {
    this.employees.push(employee);
    return this.http.post('http://localhost:8090/api/employees', employee);
  }
  getEmployees() {
    return this.http.get<Employee[]>('http://localhost:8090/api/employees');
  }
  updateEmployee(employee: Employee) {
    return this.http.put(`http://localhost:8090/api/employees`, employee);
  }
  deleteEmployee(eid: number) {
    return this.http.delete(`http://localhost:8090/api/employee/${eid}`);
  }
  getEmployee(eid: number) {
    return this.http.get<String>(`http://localhost:8090/api/employees/${eid}`);
  }
}
