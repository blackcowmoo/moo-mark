import React from 'react';
import ReactDOMServer from 'react-dom/server';
import Convert from 'ansi-to-html';

const convert = new Convert();

const Style: React.FC<{}> = () => {
  return (
    <style
      dangerouslySetInnerHTML={{
        __html: `
          body {
            margin: 0;
            background-color: #24292e;
          }

          ul {
            list-style: none;
            padding: 5px;
            display: flex;
            flex-direction: column;
          }

          li {
            display: flex;
            flex-direction: row;
          }

          li > a {
            color: #959da5;
            font-weight: normal;
            padding-right: 7.5px;
          }

          p {
            margin: 0;
            color: #f6f8fa;
          }`,
      }}
    />
  );
};

interface LogProps {
  index: number;
  log: Log;
}

const Log: React.FC<LogProps> = (props: LogProps) => {
  const { index, log } = props;
  const { text, timestamp } = log;
  const html = convert.toHtml(text);
  const time = timestamp.padEnd(30, ' ');
  return (
    <li id={time}>
      <a href={`#${time}`}>{index}</a>
      <span>{time}</span>
      <p dangerouslySetInnerHTML={{ __html: html }} />
    </li>
  );
};

export const renderLogs = (logs: Log[]): string => {
  return ReactDOMServer.renderToStaticMarkup(
    <>
      <Style />
      <ul>
        {logs.map((log, index) => (
          <Log key={log.timestamp} log={log} />
        ))}
      </ul>
    </>,
  );
};
