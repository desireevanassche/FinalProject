import { Post } from './post';
import { User } from './user';

export class Comment {
  id: number;
  inReplyToId: number | null;
  content: string | null;
  createAt: string | null;
  active: boolean | null;
  post: Post;
  user: User;

  constructor(
    id: number = 0,
    inReplyToId: number = 0,
    content: string = '',
    createAt: string = '',
    active: boolean = true,
    post: Post = new Post(),
    user: User = new User()
  ) {
    this.id = id;
    this.inReplyToId = inReplyToId;
    this.content = content;
    this.createAt = createAt;
    this.active = active;
    this.post = post;
    this.user = user;
  }
}
