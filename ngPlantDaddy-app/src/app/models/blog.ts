
export class Blog {
  id: number;
  userId: number;
  title: string;
  content: string;
  createdAt: string | null;
  updateDate: string | null;
  active: boolean | null;
  imageUrl: string | null;

  constructor(
    id: number = 0,
    userId: number = 0,
    title: string = "",
    content: string =  "",
    createdAt: string =  "",
    updateDate: string = "",
    active: boolean =  false,
    imageUrl : string = ""
  ) {

    this.id = id;
    this.userId = userId;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
    this.updateDate = updateDate;
    this.active = active;
    this.imageUrl = imageUrl;
  }
}
