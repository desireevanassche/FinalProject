import { Post } from './post';
import { User } from './user';

export class Comment {
  id: number;
  inReplyToId: number | null;
  content: string | null;
  createDate: string | null;
  active: boolean | null;
  post: Post;
  user: User;

  constructor(
    id: number = 0,
    inReplyToId: number = 0,
    content: string = '',
    createDate: string = '',
    active: boolean = true,
    post: Post = new Post(),
    user: User = new User()
  ) {
    this.id = id;
    this.inReplyToId = inReplyToId;
    this.content = content;
    this.createDate = createDate;
    this.active = active;
    this.post = post;
    this.user = user;
  }
}
