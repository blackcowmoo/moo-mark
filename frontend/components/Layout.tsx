import { ReactNode } from 'react';
import Link from 'next/link';

type Props = {
  children?: ReactNode;
};

const Layout: React.FC = ({ children }: Props) => (
  <div>
    <header className="header">
      <div className = "logo">logo</div>
      <nav className="moomark-gnb">
        <Link href="/">
          <a>Home</a>
        </Link>{' '}
        |{' '}
        <Link href="/about">
          <a>BookMark</a>
        </Link>{' '}
        |{' '}
        <Link href="/users">
          <a>Trends</a>
        </Link>{' '}
        | <a href="/api/users">MyPage</a>
      </nav>
    </header>

    <main id="main">
      <div className="container">
        <div className="side">left side</div>
        <div className="middle">{children}</div>
        <div className="side">right side</div>
      </div>
    </main>

    <footer className="footer">
      <hr />
      mooMark |{' '}
      <a href="https://github.com/blackcowmoo/moo-mark" rel="noreferrer noopener" target="_blank">
        github
      </a>
    </footer>
  </div>
);

export default Layout;
