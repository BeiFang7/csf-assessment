import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit{

  //initialise a FormGroup
  searchForm!: FormGroup

  // const nonWhiteSpace = (ctrl: AbstractControl) => {
  //   if (ctrl.value.trim().length > 0)
  //     return (null)
  //   return {nonWhiteSpace:true} as ValidationErrors
  // }

  constructor(private fb: FormBuilder, private router:Router){}

  ngOnInit(): void {
    this.searchForm = this.createSearchForm()
  }

  createSearchForm():FormGroup{
    
    this.searchForm = this.fb.group({
      movieName: this.fb.control<string>('', [
        Validators.required,
        Validators.minLength(2),
        // Validators.nonWhiteSpace,
        //to ensure movieName dont start and end with any blank spaces
        Validators.pattern(/^(?!\s)(?!.*\s$)[\w\s]+$/),
      ]),
    });
    return this.searchForm
  }

  search(){
    const movieName = this.searchForm.value['movieName']
    console.log(">>> search movieName:",movieName)
    this.router.navigate(['/search'],{queryParams: {query:movieName}})
  }

  isSearchFormInvalid(ctrlName:string):boolean{
    const ctrl = this.searchForm.get(ctrlName) as FormControl
    return ctrl.invalid && (!ctrl.pristine)
  }

}
