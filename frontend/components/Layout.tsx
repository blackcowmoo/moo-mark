import { ReactNode } from 'react';
import Link from 'next/link';

type Props = {
  children?: ReactNode;
};

const Layout: React.FC = ({ children }: Props) => (
  <div>
    <header>
      <nav>
        <Link href="/">
          <a>Home</a>
        </Link>{' '}
        |{' '}
        <Link href="/about">
          <a>About</a>
        </Link>{' '}
        |{' '}
        <Link href="/users">
          <a>Users List</a>
        </Link>{' '}
        | <a href="/api/users">Users API</a>
      </nav>
    </header>
    {children}
    <footer>
      <hr />
      mooMark |{' '}
      <a href="https://github.com/blackcowmoo/moo-mark" rel="noreferrer noopener" target="_blank">
        github
      </a>
    </footer>
  </div>
);

export default Layout;
