import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EmployeeService } from '../shared/employee.service';
import { Employee } from '../shared/employee.model';

@Component({
  selector: 'app-employee',
  standalone: false,
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css',
})
export class EmployeeComponent implements OnInit {
  constructor(public empService: EmployeeService) {}
  update: boolean = false;
  ngOnInit(): void {
    this.resetForm();
  }
  onSubmit(form: NgForm) {
    console.log(form.value);
    if (!this.update) {
      this.empService.postEmployee(form.value).subscribe((res) => {
        console.log('Employee added successfully');
        this.resetForm(form);
      });
    } else {
      this.empService.updateEmployee(form.value).subscribe((res) => {
        console.log('Employee updated successfully');
        this.resetForm(form);
        this.update = false;
      });
    }
  }

  resetForm(form?: NgForm) {
    if (form) form.reset();
    this.update = false;
    this.empService.selectedEmployee = {
      eid: 0,
      name: '',
      age: 0,
      designation: '',
      salary: 0,
    };
    this.refreshEmployees();
  }

  refreshEmployees() {
    this.empService.getEmployees().subscribe(
      (res) => {
        this.empService.employees = res;
      },
      (err) => console.error(err)
    );
  }

  onEdit(emp: Employee): void {
    this.update = true;
    this.empService.selectedEmployee = emp;
  }

  onDelete(eid: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.empService.deleteEmployee(eid).subscribe((res) => {
        console.log(res);
        this.refreshEmployees();
      });
    }
  }
}
