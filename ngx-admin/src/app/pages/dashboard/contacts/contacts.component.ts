import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { EmployeService } from '../../../services/employe.service'; // Adjust the path as necessary
import { Employe } from '../../../model/employe';

@Component({
  selector: 'ngx-contacts',
  styleUrls: ['./contacts.component.scss'],
  templateUrl: './contacts.component.html',
})
export class ContactsComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();

  employeNames: Employe[] = [];

  constructor(private employeService: EmployeService) {}

  ngOnInit() {
    this.employeService.getAllEmployeNamesJPQL()
      .pipe(takeUntil(this.destroy$))
      .subscribe(names => {
        this.employeNames = names;
      });
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
