export class Growthdata {

  id : number;
  height: number | null;
  spread: number | null;
  potDiameter:number | null;
  growthDescription: string | null;
  growthImage: string | null;
  createDate: string | null;



constructor(
  id: number = 0,
  height: number = 0,
  spread: number = 0,
  potDiameter:number = 0,
  growthDescription: string = "",
  growthImage: string = "",
  createDate: string = ""

)
{

this.id = id;
this.height = height;
this.spread = spread;
this.potDiameter = potDiameter,
this.growthDescription = growthDescription;
this.growthImage = growthImage;
this.createDate = createDate;

}

}
