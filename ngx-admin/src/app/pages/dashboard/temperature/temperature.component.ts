import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { EntrepriseService } from '../../../services/entreprise.service'; // Adjust path as necessary
import { DirectionService } from '../../../services/direction.service'; // Adjust path as necessary
import { Entreprise } from '../../../model/entreprise'; // Adjust path as necessary
import { Direction } from '../../../model/direction';

@Component({
  selector: 'ngx-temperature',
  styleUrls: ['./temperature.component.scss'],
  templateUrl: './temperature.component.html',
})
export class TemperatureComponent implements OnDestroy {
  private destroy$ = new Subject<void>();

  entreprises: Entreprise[] = [];
  directions: Direction[] = [];
  theme: any;

  constructor(
    private entrepriseService: EntrepriseService,
    private directionService: DirectionService,
  ) {}

  ngOnInit() {
    this.loadEntreprises();
    this.loadDirections();
  }

  loadEntreprises() {
    this.entrepriseService.getEntreprises()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => {
        this.entreprises = data;
      });
  }

  loadDirections() {
    this.directionService.getDirections()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => {
        this.directions = data;
      });
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

